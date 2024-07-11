# Stack/Queue
## 출력에 규칙 적용
### 뒤에서 부터 꺼내기

> Stack

- LIFO(Last In First Out)
  - 제일 늦게 들어간 놈이 제일 먼저 나온다. 
  - 들어간 순서의 역순으로 데이터 조회

![img.png](image/stack.png)
### 앞에서 부터 꺼내기

> Queue

- FIFO(First In First Out)
  - 제일 먼저 들어간 놈이 제일 먼저 나온다. 
  - 들어간 순서의 순방향으로 데이터 조회

![img.png](image/Queue.png)

## Java에서 Stack/Queue 사용
### Queue
- Java에서 Queue는 `Queue<E>` 인터페이스로만 제공해준다. 관련된 문서는 [Java Api Docs](https://docs.oracle.com/javase/8/docs/api/)에 나와있다.
- `Queue` 인터페이스는 `LinkedList`를 implement 하고있다. 
  - 따라서 `Queue<String> queue = new LinkedList<>()` 식으로 많이 사용한다.
- **Queue 메서드 요약**
  - `add(e)`, `remove()`, `element()`는 큐가 비어있을 때 예외를 발생시킨다.
  - `offer(e)`, `poll()`, `peek()`는 예외를 발생시키지 않고 특정 값을 반환한다. (ex. `null`)

|  | Throws exception | Returns special value |
| --- | --- | --- |
| **Insert** | `add(e)` | `offer(e)` |
| **Remove** | `remove()` | `poll()` |
| **Examine** | `element()` | `peek()` |

### Stack
- Java에서 Stack은 Queue와 달리 `Stack<E>` 클래스로 제공해준다. 관련된 문서는 [Java Api Docs](https://docs.oracle.com/javase/8/docs/api/)에 나와있다.
  - 따라서 `Stack<String> stack = new Stack<>()` 식으로 많이 사용한다.
- `Stack` 클래스는 `Vector` 클래스를 상속받고 있다.
- **Stack 메서드 요약**

| **Modifier and Type** | **Method** | **Description** |
| --- | --- | --- |
| **boolean** | `empty()` | Tests if this stack is empty. |
| **E** | `peek()` | Looks at the object at the top of this stack without removing it from the stack. |
| **E** | `pop()` | Removes the object at the top of this stack and returns that object as the value of this function. |
| **E** | `push(E item)` | Pushes an item onto the top of this stack. |
| **int** | `search(Object o)` | Returns the 1-based position where an object is on this stack. |

### Deque
- Stack과 Queue를 합친 자료구조로 앞, 뒤 양쪽에서 데이터를 넣고 뺄 수 있다.
  - Stack과 Queue는 데이터를 추가하면 뒤에서만 추가된다.
  - 꺼낼 때 Stack은 뒤에서, Queue는 앞에서 꺼낸다.
- Java에서 Deque는 Queue와 마찬가지로 `Deque<E>` 인터페이스로만 제공해준다. 관련된 문서는 [Java Api Docs](https://docs.oracle.com/javase/8/docs/api/)에 나와있다.
  - `Deque` 인터페이스는 `LinkedList`를 implement 하고있다. 
  - 따라서 `Deque<String> deque = new LinkedList<>()` 식으로 많이 사용한다.
- **Deque 메서드 요약**

|  | **First Element (Head)** | **First Element (Head)**    | **Last Element (Tail)** | **Last Element (Tail)** | 
|---|---| --- | --- | --- |
|             | **Throws exception** | **Special value**        | **Throws exception** | **Special value** | 
| **Insert**  | `addFirst(e)`            | `offerFirst(e)` | `addLast(e)` | `offerLast(e)` | 
| **Remove**  | `removeFirst()`          | `pollFirst()` | `removeLast()` | `pollLast()` | 
| **Examine** | `getFirst()`             | `peekFirst()` | `getLast()` | `peekLast()` |

- **Queue와 Dequeu 메서드 비교**

| **Queue Method** | **Equivalent Deque Method** |
| --- | --- |
| `add(e)` | `addLast(e)` |
| `offer(e)` | `offerLast(e)` |
| `remove()` | `removeFirst()` |
| `poll()` | `pollFirst()` |
| `element()` | `getFirst()` |
| `peek()` | `peekFirst()` |

- **Stack과 Dequeu 메서드 비교**

| **Stack Method** | **Equivalent Deque Method** |
| --- | --- |
| `push(e)` | `addFirst(e)` |
| `pop()` | `removeFirst()` |
| `peek()` | `peekFirst()` |



