# ⚙️ Data Structures and Algorithms 
*(Struktury Danych i Algorytmy)*

[![Language](https://img.shields.io/badge/Language-Java_%2F_Python-blue)]()
[![Course](https://img.shields.io/badge/Course-AiSD_%28WUST%29-F7931E)]()
[![Semester](https://img.shields.io/badge/Semester-Summer_2024%2F25-013243)]()

---

## 🇵🇱

### 📖 O projekcie
Repozytorium zawiera obszerny zbiór implementacji klasycznych struktur danych i algorytmów. Projekty zostały zrealizowane od zera w ramach zajęć laboratoryjnych z przedmiotu **Struktury Danych i Algorytmy** w **semestrze letnim 2024/25**. 

Głównym celem projektu nie było korzystanie z gotowych bibliotek, ale **dogłębne zrozumienie mechaniki działania algorytmów "pod maską"**, optymalizacja złożoności obliczeniowej (Notacja Big-O) oraz skuteczne zarządzanie pamięcią maszynową.

### 📂 Zawartość list (Szczegółowy przegląd kodu)

Kod został zorganizowany chronologicznie według kolejnych laboratoriów, obrazując stopniowy wzrost poziomu zaawansowania struktur:

* **`lab1` (Podstawy i Iteratory):** Implementacja własnych, generycznych tablic dynamicznych, obsługa wskaźników/referencji, budowa własnych Iteratorów oraz analiza podstawowej złożoności obliczeniowej.
* **`lab2` (Struktury Liniowe):** Implementacja List Jednokierunkowych (Singly Linked List), stosów (Stack) z obsługą LIFO oraz kolejek (Queue) z obsługą FIFO. 
* **`lab2-mod` (Optymalizacje List):** Zmodyfikowana, bardziej złożona wersja struktur z Lab 2 – np. Listy Dwukierunkowe (Doubly Linked List), kolejki cykliczne czy dodanie strażników (sentinels) optymalizujących przypadki brzegowe.
* **`lab3` (Algorytmy Sortowania):** Zbiór metod sortujących wbudowanych od zera. Od algorytmów kwadratowych $O(n^2)$ (Bubble, Insertion, Selection Sort) po wysoce zoptymalizowane podejścia dziel-i-zwyciężaj $O(n \log n)$ (QuickSort, MergeSort). Dołączone testy wydajnościowe.
* **`lab4` (Struktury Drzewiaste):** Implementacja podstawowych drzew binarnych oraz Drzew Poszukiwań Binarnych (BST). Oskryptowanie operacji `insert`, `search` oraz metod przechodzenia drzewa (Inorder, Preorder, Postorder).
* **`lab4-mod` (Zaawansowane Drzewa):** Rozszerzenie BST o metody usuwania węzłów z dwójką dzieci (znajdowanie następnika/poprzednika) lub implementacja mechanizmów równoważenia drzew (drzewa AVL/Red-Black Tree).
* **`lab5` (Kopce i Kolejki Priorytetowe):** Implementacja Kopca typu Min/Max (Min-Heap / Max-Heap) opartego na tablicy. Stworzenie własnej Kolejki Priorytetowej (Priority Queue) oraz algorytmu HeapSort.
* **`lab6` (Tablice Mieszające / Hash Tables):** Budowa własnych struktur słownikowych. Implementacja funkcji haszujących (Hash Functions) i rozwiązywanie problemów kolizji (Metoda łańcuchowa / Adresowanie otwarte).
* **`lab7` (Grafy - Reprezentacja i Przeszukiwanie):** Projektowanie struktur grafowych przy użyciu Macierzy Sąsiedztwa (Adjacency Matrix) oraz List Sąsiedztwa. Implementacja klasycznych algorytmów przeszukiwania: Wszerz (BFS) oraz W Głąb (DFS).
* **`lab8` & `lab9` (Zaawansowane Algorytmy Grafowe):** Algorytmy optymalizacyjne w grafach ważonych. Wyszukiwanie najkrótszych ścieżek (np. Algorytm Dijkstry) oraz wyznaczanie Minimalnego Drzewa Rozpinającego (MST - algorytm Kruskala / Prima).
* **`lab10` (Dynamiczne Programowanie / Wzorce):** Algorytmy tekstowe (Pattern Matching) i podejście programowania dynamicznego (Dynamic Programming) do optymalizacji złożonych problemów z nakładającymi się podproblemami.
* **`parsers` (Parsery i Analiza Składniowa):** Autorski moduł przetwarzający. Zaimplementowane algorytmy tokenizacji ciągów znaków (np. wyrażenia matematyczne), algorytm stacji rozrządowej (Shunting-yard) oraz konwersja do Odwrotnej Notacji Polskiej (ONP/RPN).

### 🎯 Kluczowe kompetencje
* Świadomość **złożoności czasowej i pamięciowej** ($O(1)$, $O(\log n)$, $O(n)$, $O(n \log n)$).
* Biegłe posługiwanie się paradygmatem programowania zorientowanego obiektowo (OOP).
* Umiejętność samodzielnej implementacji "niskopoziomowych" wskaźników i referencji w językach wysokiego poziomu.
* Rozwiązywanie przypadków brzegowych (Edge-Cases) dla struktur rekurencyjnych (Drzewa, Grafy).

---

## 🇬🇧

### 📖 About the project
This repository contains a comprehensive collection of classical data structures and algorithms implemented entirely from scratch. The projects were developed during the **Data Structures and Algorithms** laboratory classes in the **Summer Semester 2024/25**.

The main goal was to avoid standard libraries and deeply understand the **internal mechanics of algorithms**, optimize computational complexity (Big-O notation), and manage memory efficiently.

### 📂 Lab Contents (Code Breakdown)

The code is divided into sequential lab sessions, demonstrating a progressive increase in architectural complexity:

* **`lab1` (Basics & Iterators):** Implementation of dynamic arrays, handling raw references, designing custom Iterators, and introductory computational complexity analysis.
* **`lab2` (Linear Structures):** Scratch-built Singly Linked Lists, Stacks (LIFO), and Queues (FIFO).
* **`lab2-mod` (List Optimizations):** Advanced modifications of Lab 2 – e.g., Doubly Linked Lists, Cyclic Queues, or implementing sentinels to optimize edge cases gracefully.
* **`lab3` (Sorting Algorithms):** A full suite of custom sorting methods. Ranging from $O(n^2)$ (Bubble, Insertion, Selection) to highly optimized Divide & Conquer algorithms in $O(n \log n)$ (QuickSort, MergeSort) along with performance benchmarking.
* **`lab4` (Tree Structures):** Implementation of Binary Search Trees (BST). Scripting `insert`, `search`, and deep tree traversal techniques (Inorder, Preorder, Postorder).
* **`lab4-mod` (Advanced Trees):** Extending the standard BST with complex node removal (finding successors) and balancing mechanisms (like AVL trees).
* **`lab5` (Heaps & Priority Queues):** Array-based Min/Max Heap data structures. Creating custom Priority Queues and implementing the HeapSort algorithm.
* **`lab6` (Hash Tables):** Building custom dictionary/map objects. Implementing custom hash functions and collision resolution strategies (Chaining / Open Addressing).
* **`lab7` (Graphs - Basics & Traversal):** Graph representations using Adjacency Matrices and Adjacency Lists. Implementation of foundational pathfinding: Breadth-First Search (BFS) and Depth-First Search (DFS).
* **`lab8` & `lab9` (Advanced Graph Algorithms):** Optimization in weighted graphs. Finding shortest paths (e.g., Dijkstra's Algorithm) and computing the Minimum Spanning Tree (MST using Kruskal's / Prim's algorithms).
* **`lab10` (Dynamic Programming / Strings):** Implementations of pattern-matching algorithms and Dynamic Programming techniques to solve overlapping subproblem bottlenecks.
* **`parsers` (Syntax Analysis & RPN):** A standalone module for text and expression processing. Includes custom tokenizers, the Shunting-yard algorithm, and Reverse Polish Notation (RPN) logic tree evaluations.

### 🎯 Key Skills Demonstrated
* Strong understanding of **time and space complexity** ($O(1)$, $O(\log n)$, $O(n)$, $O(n \log n)$).
* Object-Oriented Programming (OOP) applied strictly to data structure design.
* Edge-case analysis and algorithm bottleneck optimization (especially in recursive trees and graphs).
* Experience with IDE-based environments (JetBrains / `.idea` configuration).