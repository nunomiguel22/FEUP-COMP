# Examples
The following extra examples are also used in the jasmin unit tests.

## BubbleSort

### Code

```java
import static io.println(int) void;

class BubbleSort {
    int[] arr;
    
    public int bubbleSort(int[] arr) { 
        int n;
        int i;
        boolean sorted;
        int temp;
         
        i = 0;
        sorted = false;
        n = this.getArr().length;
        
        while(!sorted){
            sorted = true;
            i = 0;
            while (i < n - 1){
                if (arr[i + 1] < arr[i]){
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    sorted = false;
                }
                else {}
                i = i + 1;
            }
        }
        return 0;
    } 
  
    public int printArray(int[] arr) { 
        int n;
        int i;
        
        {
            {
        i = 0;
        n = arr.length;
            }
        }

        while (i < n){
            io.println(this.getArr()[i]);
            i = i + 1;
        }
        return 0;
    } 

    public int[] getArr(){
        return arr;
    }

    public boolean initArray(){
        arr = new int[7];
        arr[0] = 64;
        arr[1] = 34;
        arr[2] = 25;
        arr[3] = 12;
        arr[4] = 22;
        arr[5] = 11;
        arr[6] = 90;
        return false;
    }

    public static void main(String[] args) { 
        BubbleSort ob;  
        int i;

        ob = new BubbleSort();
        ob.initArray();
        
        ob.bubbleSort(ob.getArr());
        ob.printArray(ob.getArr()); 
    } 
} 
```

### Output:
```
11
12
22
25
34
64
90
```

## IntArrayList

### Code

```java
import static io.println(int) void;

class IntArrayList{
    int[] arr;
    int size;

    public int init(){
        arr = new int[1];
        size = 0;
        return 0;
    }

    public int add(int value){
        if (arr.length - size < 2)
            this.grow();
        else {}

        arr[size] = value;
        size = size + 1;
        return 0;
    }

    public int get(int index){
        return arr[index];
    }

    public int grow(){
        int[] temp;
        int i;
        i = 0;
        
        temp = new int[arr.length * 2];
        
        while(i < arr.length){
            temp[i] = arr[i];
            i = i + 1;
        }
        arr = temp;
        return 0;
    }

    public int size(){
        return size;
    }

    public int print(){
        int i;
        i = 0;

        while(i < this.size()){
            io.println(this.get(i));
            i = i + 1;
        }
        return 0;
    }

    public static void main(String[] args){
        IntArrayList list;
        list = new IntArrayList();
        list.init();
        list.add(3);
        list.add(5);
        list.add(22);
        list.add(14);
        list.add(122);
        list.add(1);
        list.print();

    }
}
```

### Output:
```
3
5
22
14
122
1
```

## Divisors

### Code

```java
import IntArrayList;
import IntArrayList.init() int;
import IntArrayList.add(int) int;
import IntArrayList.print() int;
import static io.println(int) void;

// Requires IntArrayList to be compiled before

class Divisors{

    IntArrayList divisors;
    int value;

    public int init(int val){
        divisors = new IntArrayList();
        divisors.init();
        value = val;
        return 0;
    }

    public int calcDivisors(){
      int i;
      int r;
      
      i= 1;

      while(i < value + 1){
        r = this.remainder(value, i);
        if (this.eq(r, 0))
          divisors.add(i);
        else {}
        i = i + 1;
      }
      return 0;
    }

    public IntArrayList getDivisors(){
      return divisors;
    }

    public static void main(String[] args){
      Divisors div;
      IntArrayList divList;
      
      div = new Divisors();
      div.init(45);
      div.calcDivisors();
      divList = div.getDivisors();
      divList.print();
    }

    public int remainder(int num, int divisor){
        return (num - divisor * (num / divisor));
    }

    // From Life.jmm
    public boolean eq(int a, int b) {
		return (!this.lt(a, b) && !this.lt(b, a));
	}
	
	public boolean ne(int a, int b) {
		return (!this.eq(a, b));
	}
	
    public boolean lt(int a, int b) {
		return (a < b);
    }
    
    public boolean le(int a, int b) {
		return !(!this.lt(a, b) && !this.eq(a, b));
    }
    
    public boolean gt(int a, int b) {
		return (!this.le(a, b));
    }
    
    public boolean ge(int a, int b) {
		return !(!this.gt(a, b) && !this.eq(a, b));
	}
}
```

### Output:
```
1
3
5
9
15
45
```

## Fibonacii

### Code

```java
import static io.println(int) void;

//Recursion test
class Fibonacci {

    public int fib(int n){
        int result;

        if (n < 2)
            result = n;
        else
            result = this.fib(n-1) + this.fib(n-2);
        
        return result;
    }

    public static void main(String[] args){
        Fibonacci fb;
        fb = new Fibonacci();
        io.println(fb.fib(2));
        io.println(fb.fib(8));
        io.println(fb.fib(9));
        io.println(fb.fib(10));
    }
}
```

### Output:
```
1
21
34
55
```
