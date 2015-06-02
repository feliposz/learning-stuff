x=1
while [ $x -le 100 ] ; do
	if [ `expr $x % 3` -eq 0 -a `expr $x % 5` -eq 0 ] ; then	
		echo FizzBuzz
	elif [ `expr $x % 3` -eq 0 ] ; then	
		echo Fizz
	elif [ `expr $x % 5` -eq 0 ] ; then	
		echo Buzz
	else
		echo $x
	fi
	x=`expr $x + 1`
done

