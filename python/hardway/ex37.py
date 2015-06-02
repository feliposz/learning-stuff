from os import listdir
import re

x = "hello, i'm global x"

class SourceFileError(IOError):
    pass

class Animal:
    pass

class Mammal(Animal):
    pass
    
class Dog(Mammal):
    pass

class Cat(Mammal):
    pass
    
def my_iter(x):
    for y in range(1, 10):
        yield x * y

def countdown(x):
    while x > 0:
        print('%i...' % x)
        x -= 1
    print('the end')

def double(fn, a):
    return fn(fn(a))

def source_file(filename):
    content = None
    try:
        f = open(filename)
        content = f.read()
    except IOError as e:
        raise SourceFileError('Error opening source file "%s". Details: %s' % (filename, e))
    return content

def count_words(filename):
    words = 0
    with open(filename) as f:
        while True:
            line = f.readline()
            if line == '':
                break
            else:
                words += len(line.split())
    return words

def spam_and_eggs():
    for i in range(20):
        if i % 3 == 0 and i % 5 == 0:
            print('spam')
        elif i % 5 == 0:
            print('eggs')
        elif i % 3 == 0:
            print('bacon')
        else:
            continue
        print('...')

def pause():
    while True:
        answer = raw_input('continue? ')
        if answer.lower() in ('y', 'yes', 'yeah', 'yup', 'yep'):
            break
        elif answer == 'no' or answer == 'No' or answer == 'NO' or answer == 'nay':
            continue
        else:
            print('er... "%s" what?' % answer)

def run_all_prints(filename):
    with open(filename) as f:
        for line in f.readlines():
            line = line.strip()
            if 'print' in line and 'def' not in line and 'run_all_prints' not in line:
                try:
                    print('executing: %s' % line)
                    exec line
                except:
                    print('could not run: %s ' % line)
                finally:
                    print('--')
            else:
                pass

def delete_some():
    str_a = 'abcdefghijklmnopqrstuvwxyz'
    a = [c for c in str_a]
    for i in reversed(range(len(a))):
        if i % 2 == 0:
            del(a[i])
    print(''.join(a))

def set_x():
    global x
    x = "hello i'm, modified global x"

def print_x():
    print(x)

def list_scripts():
    files = listdir('.')
    scripts = []
    for file in files:
        if re.match('.*\.py$', file):
            scripts.append(file)
    print('; '.join(scripts))

def animals():
    garfield = Cat()
    if isinstance(garfield, Cat):
        print "It's a cat"
    if isinstance(garfield, Dog):
        print "It's a dog"
    if isinstance(garfield, Mammal):
        print "It's a mammal"
    if isinstance(garfield, Animal):
        print "It's an animal"
    if garfield is None:
        print "It's nothing"
    if garfield is not None:
        print "It's something"
        
if __name__ == '__main__':
    for n in my_iter(9):
        print(n)
    countdown(5)
    assert 1 == 1, '1 is equal to 1'
    print(double(lambda x: x*2, 123))
    print(double(lambda s: '"' + s + '"', 'abc'))
    print('source length: %d' % len(source_file('ex37.py')))
    print(count_words('ex37.py'))
    #pause()
    spam_and_eggs()
    #pause()
    run_all_prints('ex37.py')
    delete_some()
    set_x()
    print_x()
    list_scripts()
    animals()