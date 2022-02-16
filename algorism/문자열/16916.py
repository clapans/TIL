s = input()
word = input()
arr = [0] * len(word)
j = 0

for i in range(1,len(word)):
    while j > 0 and word[i] != word[j]:
        j = arr[j-1]
    if word[i] == word[j]:
        j += 1
        arr[i] = j

j = 0

for i in range(len(s)):
    while j > 0 and s[i] != word[j]:
        j = arr[j-1]
    if s[i] == word[j]:
        if j == len(word) - 1:
            print(1)
            quit(0)
        else:
            j += 1

print(0)
