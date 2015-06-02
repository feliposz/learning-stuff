import sys

def hello(name):
    name = name + "!!!!"
    print("Hello,", name)
    
def main():
    if len(sys.argv) <= 1:
        print("Hello, World!")
    else:
        hello(sys.argv[1])

if __name__ == "__main__":
    main()
