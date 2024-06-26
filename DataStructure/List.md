# Array and List
## Array
### 특징
- 👍 여러개의 데이터를 한꺼번에 다룰 수 있다.
- 🤞 Array는 Object는 아니지만 Reference Value로 취급된다.
- 🤞 메모리상에 연달아 공간을 확보해야 한다.
- 👎 미리 공간을 확보해 놓고 써야 한다.
- 👎 한 번 만들어진 공간은 크기가 고정된다.
- 👍 첫 번째 위치만 알면 index로 상대적 위치를 빠르게 찾을 수 있다.

### 불편한 점
- 유연하지 못하다.
- 미리 몇개가 필요한 지 몰라도 쓸 수는 없을까?
- 필요에 따라 크기가 늘어나거나 줄어들 수는 없을까?

> 이 불편한 문제점을 해결할 수 있는 것이 List❗️

## List
- 👍 여러개의 데이터를 한꺼번에 다룰 수 있다.
- 👍 메모리상에 연속되지 않아도 된다.
- 👍 미리 공간을 확보해 놓지 않아도 된다.
- 👍 필요에 따라 데이터가 늘어나거나 줄어든다.
- 👎 첫 번째 위치로 부터 index로 목표위치를 알려면 한 칸 한 칸 이동하면서 찾아야 한다.

### LinkedList
- 자바에서 List관련 최상위 클래스는 `LinkedList` 이다. 관련된 문서는 [Java Api Docs](https://docs.oracle.com/javase/8/docs/api/)에 나와있다. 
- `LinkedList`가 implement하고 있는 클래스들은 다음과 같다.
  - `Serializable`, `Cloneable`, `Iterable<E>`, `Collection<E>`, `Deque<E>`, `List<E>`, `Queue<E>`

### List
- `List`는 `LinkedList`에서 implemented된 클래스이다.
- `LinkedList`에서 추가로 더 많은 함수를 가지고 있기 때문에 `LinkedList`타입을 만든다 하더라도 `List`타입으로 표현하는 경우가 많다.
  - ```java 
    List<Integer> list = new LinkedList<>();
    ```

### ArrayList
- `LinkedList`에서 원하는 index에서 값을 빠르게 찾아올 수 있는 Array의 특징을 추가한 `ArrayList`가 있다.
- `ArrayList`를 사용하면 `get(index)`함수를 통해 원하는 인덱스의 값을 구할 수 있다.

### ArrayList vs Vector
- `ArrayList`와 `Vector` 모두 `List`클래스를 implement하기 때문에 둘다 `List`타입으로 많이 표현한다.
- `ArrayList` 와 `Vector`는 거의 모든 부분이 비슷하지만 synchronize(동기화) 에서 차이가 있다.
  - `ArrayList` -> not synchronized
  - `Vector` -> synchronized 
- 따라서 thread-safe한 구현을 원한다면 `ArrayList`, 그렇지 않다면 `Vector`를 사용하는 것을 추천한다.
> thread-safe: 여러 스레드로부터 동시에 접근되어도 안전하게 사용할 수 있는 속성

## 예제 코드
### 문제1
주어진 입력중 최대값을 구하고, 최대값이 이 위치하는 index 값의 목록을 반환하세요.

- 입력: `[1, 3, 5, 4, 5, 2, 1]`

입력된 목록의 최대값은 5입니다. <br>
5와 동일한 값을 가진 위치는 3번째, 5번째 위치 입니다. <br>
이 위치에 해당하는 index는 `[2, 4]` 입니다.
- 출력: `[2, 4]`

```java
import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] arr) {
        // max에 의한 반환값이 OptionalInt 이기 때문데 getAsInt()를 붙여줘야 한다.
        int max = Arrays.stream(arr).max().getAsInt(); 
        
        return IntStream.range(0, arr.length)
            .filter(i -> arr[i] == max)
            .toArray();
    }
}
```

### 문제2
문제 설명

길이가 n인 배열에 1부터 n까지 숫자가 중복 없이 한 번씩 들어 있는지를 확인하려고 합니다.
1부터 n까지 숫자가 중복 없이 한 번씩 들어 있는 경우 true를, 아닌 경우 false를 반환하도록 함수 solution을 완성해주세요.

제한사항
- 배열의 길이는 10만 이하입니다.
- 배열의 원소는 0 이상 10만 이하인 정수입니다.

입출력 예

- arr: `[4, 1, 3, 2]`
- result: `true`
- arr: `[4, 1, 3]`
- result: `false`

입출력 예 설명

- 입출력 예 #1 <br>
입력이 `[4, 1, 3, 2]`가 주어진 경우, 배열의 길이가 4이므로 배열에는 1부터 4까지 숫자가 모두 들어 있어야 합니다. [4, 1, 3, 2]에는 1부터 4까지의 숫자가 모두 들어 있으므로 true를 반환하면 됩니다.

- 입출력 예 #2 <br>
`[4, 1, 3]`이 주어진 경우, 배열의 길이가 3이므로 배열에는 1부터 3까지 숫자가 모두 들어 있어야 합니다. [4, 1, 3]에는 2가 없고 4가 있으므로 false를 반환하면 됩니다.

```java
import java.util.*;

class Solution {
    public boolean solution(int[] arr) {
        int[] answer = new int[arr.length];
        for (int i = 0; i < arr.length; i++) answer[i] = i+1;
        
        Arrays.sort(arr);
        
        return Arrays.equals(arr, answer);
    }
}
```
> sort() 의 시간 복잡도는 O(nlogn)이다.

### 문제3
문제 설명
- 자연수 n을 뒤집어 각 자리 숫자를 원소로 가지는 배열 형태로 리턴해주세요. 예를들어 n이 12345이면 [5,4,3,2,1]을 리턴합니다.

제한 조건
- n은 10,000,000,000이하인 자연수입니다.

입출력 예
- n: `12345`
- return: `[5,4,3,2,1]`

```java
import java.util.*;

class Solution {
    public int[] solution(long n) {
        List<Integer> list = new LinkedList<>();

        while(n > 0) {
            list.add((int)(n % 10);
            n /= 10;
        }

        return list.stream().mapToInt(Integer::intValue).toArrays();
    }
}
```
