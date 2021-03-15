# File Reader Task


Write a program that reads a file and prints out (e.g. to System.out) the last N lines of the file, supporting these input parameters:
* first paramemter: a string - the name of the file, which could be located in the same directory
* second parameter: an integer - the (max.) number of lines to print
* third parameter (opt.): a string - specifies what type of filter to apply. Accepted values: '-startsWith', '-endsWith', '-contains'
* fourth parameter (opt.): a string - the string will be used as a filter. When the fourth parameter is provided only these lines from the last N lines will be printed, which match at least one of the following two conditions:
** in case of '-startsWith' filter start with the specified string; in case of '-endsWith' filter end with the specified string; in case of '-contains' filter contain the specified string
** are directly before a line, matching the specified above filter
Ideally, a validation for the input parameters could be implemented

Example:

Input file:
A
B
C
D
at 1
at 2
at 3
E
F
at 4
G
H
J

Program arguments:
"File1.txt" 10 -startsWith at

Output:
D
at 1
at 2
at 3
F
at 4

