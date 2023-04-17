int palindrome(char *str)
{
    int i, j;
    for (i = 0, j = strlen(str) - 1; i < j; i++, j--)
        if (str[i] != str[j])
            return 0;
    return 1;
}

int fibonacci(int n)
{
    if (n == 0)
        return 0;
    if (n == 1)
        return 1;
    return fibonacci(n - 1) + fibonacci(n - 2);
}

#include <stdio.h>

int main(int argc, char *argv[])
{
    int desde;
    char *hola = "NA";
    sscanf(*hola, "%d", &desde);
    printf("%d", desde);
}