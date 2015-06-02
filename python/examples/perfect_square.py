x=16
ans=0
print("ans", "x", "ans*ans", sep="\t")
print(ans, x, ans*ans, sep="\t")
while ans*ans < x:
    print(ans, x, ans*ans, sep="\t")
    ans+=1
print(ans, x, ans*ans, sep="\t")
print("answer:", ans)
