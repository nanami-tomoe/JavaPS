# Linear Search
## Array와 List의 공통점은?
### Array
1. **여러개의 데이터를 한 번에 다룰 수 있다.**
2. Array는 Object는 아니지만 Reference Value로 취급된다. (주소 값을 지정)
3. 메모리상에 연달아 공간을 확보한다.
4. 미리 공간을 확보해 놓고 써야 한다.
5. 한 번 만들어진 공간은 크기가 고정된다.
6. 첫 번째 위치만 알면 index로 상대적 위치를 빠르게 찾을 수 있다.

### List
1. **여러개의 데이터를 한 번에 다룰 수 있다.**
2. 메모리상에 연속되지 않아도 된다.
3. 미리 공간을 확보해 놓지 않아도 된다.
4. 필요에 따라 데이터가 늘어나거나 줄어든다.
5. 첫 번째 위치로 부터 index로 목표위치를 알려면 한 칸 한 칸 이동하면서 찾아야 한다.

> **공통점**
> - 여러개의 데이터를 한 번에 다룰 수 있다.
> - 데이터가 선형적으로 구성되어 있다 -> 선형 자료구조
> - 선형 자료구조에서 데이터를 찾는 것이 **선형 탐색** 이다.

## 원하는 것을 확실하게 찾을 수 있는 방법은?
### Brute Force

> 무식한 방법이 가장 확실하다!

```java
// O(n)
int search(int[] data, int s) {
    for(int n : data) {
        if(n == s) return n;
    }
    throw new Exception("Not Found");
}
```

## 원하는 것을 빠르게 찾을 수 있는 방법은?
### Binary Search

> 범위를 좁혀라!

```java

// O(log n)
int search(int[] nums, int s) {
    int start = 0;
    int end = nums.length;
    
    while(start < end) {
        int mid = (start + end) / 2;
        
        int d = nums[mid];
        if(d == s) return mid;
        
        if(d < s) start = mid + 1;
        else end = mid;
    }
    
    throw new Exception("Not Found");
}
```

## Java에서 Linear Search

- `indexOf` 메서드 안에 Linear Search에 대한 알고리즘이 구현되어 있다.
```java
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < 100; i++) list.add(i);

        System.out.println(list);
        
        int index = list.indexOf(63);
        System.out.println(index);
    }
}
```

- Custom Data를 사용할 때
  - `toString()` 메서드를 오버라이딩 해서 출력값을 정의
  - `equals()` 메서드를 오버라이딩 해서 비교값을 정의
```java
class MyData {
    int v;
    
    public MyData(int v) {
        this.v = v;
    }
    
    @Override
    public String toString() {
        return "" + v;
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        MyData myData = (MyData) o;
        return v == myData.v;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(v);
    }
}

public class Main {
    public static void main(String[] args) {
        List<MyData> list = new LinkedList<>();

        for (int i = 0; i < 100; i++) list.add(new MyData(i));

        System.out.println(list);

        // indexOf 메서드 안에 linear search에 대한 알고리즘이 구현되어 있다.
        int index = list.indexOf(new MyData(63));
        System.out.println(index);
    }
}
```

- 랜덤값을 넣을 때

```java
import java.util.Random;

class MyData {
    int v;

    public MyData(int v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "" + v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyData myData = (MyData) o;
        return v == myData.v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v);
    }
}

public class Main {
    public static void main(String[] args) {
        List<MyData> list = new LinkedList<>();

        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            list.add(new MyData(r.nextInt(100)));
        }

        System.out.println(list);

        // indexOf 메서드 안에 linear search에 대한 알고리즘이 구현되어 있다.
        int index = list.indexOf(new MyData(63));
        System.out.println(index);
    }
}
```

> `indexOf()` 메서드는 완전 탐색을 하기 때문에 시간 복잡도가 O(n)이다.

- Bianry Search를 사용하려면?
  - `java.util` 패키지에 있는 `Collections` 클래스의 `Collections.binarySearch()` 메서드를 사용한다.
    - 자세한 사항은 [Java Api Docs](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#binarySearch-java.util.List-T-)를 참고하자.
  - `binarySearch()` 메서드는 정렬된 상태에서만 사용할 수 있다.
  - 정렬된 상태가 아니라면 음수값을 반환한다.

```java

```