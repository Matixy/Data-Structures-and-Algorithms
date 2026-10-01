package parsers;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class StatsParser {

  private static final Pattern SIZE_PATTERN = Pattern.compile("size=\\s*(\\d+).*array=\\s*(\\S+)");
  private static final Pattern TIME_PATTERN = Pattern.compile("time \\[ms\\]: ([\\d.,]+) \\+- ([\\d.,]+)");
  private static final Pattern COMP_PATTERN = Pattern.compile("comparisons: ([\\d.,]+) \\+- ([\\d.,]+)");
  private static final Pattern SWAP_PATTERN = Pattern.compile("swaps: ([\\d.,]+) \\+- ([\\d.,]+)");

  private static final List<String[]> stats = new ArrayList<>();

  public static void interceptAndRun(Runnable testRunner, String outputFilePath) throws IOException {
    PrintStream originalOut = System.out;
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    TeeOutputStream tee = new TeeOutputStream(originalOut, buffer);
    PrintStream interceptOut = new PrintStream(tee, true);
    System.setOut(interceptOut);

    testRunner.run();

    System.out.flush();
    System.setOut(originalOut);

    BufferedReader reader = new BufferedReader(new StringReader(buffer.toString()));

    String arrayType = null, size = null;
    String time = null, timeDev = null;
    String comps = null, compsDev = null;
    String swaps = "0", swapsDev = "0";

    stats.clear();

    String line;
    while ((line = reader.readLine()) != null) {
      Matcher sizeMatcher = SIZE_PATTERN.matcher(line);
      Matcher timeMatcher = TIME_PATTERN.matcher(line);
      Matcher compMatcher = COMP_PATTERN.matcher(line);
      Matcher swapMatcher = SWAP_PATTERN.matcher(line);

      if (sizeMatcher.find()) {
        size = sizeMatcher.group(1);
        arrayType = sizeMatcher.group(2);
        time = timeDev = comps = compsDev = "0";
        swaps = swapsDev = "0";
      } else if (timeMatcher.find()) {
        time = timeMatcher.group(1).replace(".", "").replace(",", ".");      // "1 234,56" → "1234.56"
        timeDev = timeMatcher.group(2).replace(".", "").replace(",", ".");
      } else if (compMatcher.find()) {
        comps = compMatcher.group(1).replace(".", "").replace(",", ".");
        compsDev = compMatcher.group(2).replace(".", "").replace(",", ".");

        reader.mark(300);
        String next = reader.readLine();
        if (next != null && SWAP_PATTERN.matcher(next).find()) {
          Matcher sm = SWAP_PATTERN.matcher(next);
          if (sm.find()) {
            swaps = sm.group(1).replace(".", "").replace(",", ".");
            swapsDev = sm.group(2).replace(".", "").replace(",", ".");
          }
        } else {
          reader.reset();
          swaps = swapsDev = "0";
        }

        if (arrayType != null && size != null) {
          stats.add(new String[]{
                  arrayType,
                  size,
                  time.replace(".", ","),       // kropkę z powrotem na przecinek dla Excela
                  timeDev.replace(".", ","),
                  comps.replace(".", ","),
                  compsDev.replace(".", ","),
                  swaps.replace(".", ","),
                  swapsDev.replace(".", ",")
          });
        }
      }
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
      writer.write("ArrayType;size;time;timeDeviation;comparisons;comparisonsDeviation;swaps;swapsDeviation\n");
      for (String[] row : stats) {
        writer.write(String.join(";", row));
        writer.write("\n");
      }
    }

    System.out.println("Zapisano dane do pliku: " + outputFilePath);
  }

}
