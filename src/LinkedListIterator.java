
/**
 * Devolopers : 
 * Muhammed Emin Çevikol No : 120315013 
 * Uğur Kafalı No : 120315021 
 * Hilmi Araz No : 130315042 
 */
 
import java.util.*;

public class LinkedListIterator<E> implements Iterator<E>
{
  private Node<E> nextNode;

  public LinkedListIterator(Node<E> head)
  {
	 nextNode = head;
  }

  public boolean hasNext()
  {
	return nextNode != null;
  }

  public E next()
  {
	 if (!hasNext()) throw new NoSuchElementException();
	 E res = nextNode.getElement();
	 nextNode = nextNode.getNext();
	 return res;
  }

  public void remove() { throw new UnsupportedOperationException(); }
}