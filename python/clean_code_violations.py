def process_data(a, b, c, d, e, f, g):
    x = 0

    if a > 10:
        if b > 20:
            if c > 30:
                if d > 40:
                    if e > 50:
                        x = a + b + c + d + e + f + g

    if x > 100:
        x = x * 0.90

    if x > 500:
        x = x * 0.80

    if x > 1000:
        x = x * 0.70

    return x
