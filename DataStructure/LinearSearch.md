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
### 완전 탐색
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

- **Custom Data를 사용할 때**
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

- **랜덤값을 넣을 때**

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

> `indexOf()` 메서드는 **완전 탐색**을 하기 때문에 시간 복잡도가 O(n)이다.

### Bianry Search를 사용하려면?
- `java.util` 패키지에 있는 `Collections` 클래스의 `Collections.binarySearch()` 메서드를 사용한다.
  - 자세한 사항은 [Java Api Docs](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#binarySearch-java.util.List-T-)를 참고하자.
  - `binarySearch()` 메서드는 정렬된 상태에서만 사용할 수 있다.
  - 정렬된 상태가 아니라면 음수값을 반환한다.
- `binarySearch()` 메서드는 첫번째 인자로 `List` 타입이 들어가는데 이 `List` 타입이 갖는 데이터 형은 `Comparble` 을 extends 해야 한다.
- `Comparable<T>` 는 인터페이스이고 그 중 `CompareTo()` 메서드를 implements 해야 한다.
```java
public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
```

```java
import java.util.*;

// Comparable<T> 인터페이스를 implements 해야 한다.
class MyData implements Comparable<MyData> {
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

  @Override
  public int compareTo(MyData o) {
    //
    return v - o.v;
  }
}

public class Main {
  public static void main(String[] args) {
    List<MyData> list = new LinkedList<>();

    // binarySearch() 메서드를 사용하려면 정렬된 상태여야 한다.
    for (int i = 0; i < 100; i++) {
      list.add(new MyData(i));
    }

    int index = Collections.binarySearch(list, new MyData(63));
    System.out.println(index);
  }
}
```
> search 는 indexOf, contains, remove 같은 곳에서 이미 구현되어 있다. : 완전 탐색
> equals 가 제공될 필요가 있다.
> 
> 이진 탐색: Collections.binarySearch
> Comparable 가 구현 되어야 한다.
> 순서대로 정렬되어 있어야 한다.

### 문제 1

- **문제 설명** <br><br>
전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다. <br>
전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다. <br><br>
전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.<br><br>
  - 구조대 : 119
  - 박준영 : 97 674 223
  - 지영석 : 11 9552 4421 <br>


- **제한 사항**<br>
  - phone_book의 길이는 1 이상 1,000,000 이하입니다.
    - 각 전화번호의 길이는 1 이상 20 이하입니다.
    - 같은 전화번호가 중복해서 들어있지 않습니다.

---

- **입출력 예제**

| phone_book | return |
| --- | --- |
| `["119", "97674223", "1195524421"]` | `false` |
| `["123","456","789"]`  | `true` |
| `["12","123","1235","567","88"]` | `false` |

- **입출력 예 설명**
  - 입출력 예 #1
    - 앞에서 설명한 예와 같습니다.

  - 입출력 예 #2
    - 한 번호가 다른 번호의 접두사인 경우가 없으므로, 답은 true입니다.

  - 입출력 예 #3
    - 첫 번째 전화번호, “12”가 두 번째 전화번호 “123”의 접두사입니다. 따라서 답은 false입니다.
---
```java
class Solution {
    public boolean solution(String[] phone_book) {
        
        for(String s1 : phone_book) {
            for(String s2 : phone_book) {
                if(s1.equals(s2)) continue;
                if(s1.startsWith(s2)) return false; // s1이 s2로 시작 된다면
            }
        }
        
        return true;
    }
}
```

### 문제 2
- **문제 설명** <br><br>
대문자와 소문자가 섞여있는 문자열 s가 주어집니다. s에 'p'의 개수와 'y'의 개수를 비교해 같으면 True, 다르면 False를 return 하는 solution를 완성하세요. 'p', 'y' 모두 하나도 없는 경우는 항상 True를 리턴합니다. 단, 개수를 비교할 때 대문자와 소문자는 구별하지 않습니다. <br><br>
예를 들어 s가 "pPoooyY"면 true를 return하고 "Pyy"라면 false를 return합니다.


- **제한사항**
  - 문자열 s의 길이 : 50 이하의 자연수
  - 문자열 s는 알파벳으로만 이루어져 있습니다.

---
- **입출력 예**

| s | answer |
| --- | --- |
| _`"pPoooyY"`_ | `true` |
| `"Pyy"` | `false` |

- **입출력 예 설명**
  - **입출력 예 #1** <br>
  'p'의 개수 2개, 'y'의 개수 2개로 같으므로 true를 return 합니다.

  - **입출력 예 #2** <br>
  'p'의 개수 1개, 'y'의 개수 2개로 다르므로 false를 return 합니다.
---
```java
class Solution {
  boolean solution(String s) {

    String str = s.toLowerCase(); // 대문자를 소문자로 변환

    int p = 0;
    int y = 0;

    for(char c : str.toCharArray()) {
      if(c == 'p') p++;
      if(c == 'y') y++;
    }

    return p == y;
  }
}
```