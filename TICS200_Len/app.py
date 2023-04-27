def log(n, b):
    if n < b:
        return 0
    else:
        return 1 + log(n / b, b)


print(log(8, 3))
