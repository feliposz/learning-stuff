x = 2
ans = 0
if x >= 0:
    while ans * ans < x:
        ans += 1
    if x != ans*ans:
        print("Not a perfect square")
    else:
        print(ans)
else:
    print("Not a positive square")
