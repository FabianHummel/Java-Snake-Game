package snakegame.library;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class JoinedList<T> {
    private Join<T> root;

    /**
     * Adds an element to the list by appending it to the end
     * @param element The element to add
     */
    public void append(T element) {
        if (root == null) {
            init(element);
            return;
        }

        var last = last();
        assert last != null;
        last.setNext(new Join<>(
            last,
            null,
            element
        ));
    }

    /**
     * Adds an element to the list by prepending it to the start
     * @param element The element to add
     */
    public void prepend(T element) {
        if(root == null) {
            init(element);
            return;
        }

        var first = first();
        root = new Join<>(
            null,
            first,
            element
        );
    }

    /**
     * Gets an element in the list by its index
     * @param index the position in the list
     * @return the element at that position
     */
    public T get(int index) {
        int i = 0;
        for (var run = root; run != null; run = run.getNext()){
            if(i++ == index)
                return run.getElement();
        } throw new NoSuchElementException("Element at index " + index + " does not exist");
    }

    /**
     * Gets the first element in the list
     * @return the first element
     */
    public T getFirst() {
        return get(0);
    }

    /**
     * Gets the last element in the list
     * @return the last element
     */
    public T getLast() {
        return get(
            size()
        );
    }

    /**
     * The amount of elements inside the list
     * @return The size of the list
     */
    public int size() {
        int i = 0;
        for (var run = root; run != null; run = run.getNext()) {
            i++;
        } return i;
    }

    /**
     * Returns true when the list does not contain any element
     * @return if the list is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Initializes the list with a starting value
     * @param element The value for initialization
     */
    private void init(T element) {
        root = new Join<>(
            null,
            null,
            element
        );
    }

    /**
     * Returns the last element in the list
     * @return The Join object of the last item
     */
    private Join<T> last() {
        for (var run = root; run != null; run = run.getNext()) {
            if (!run.hasNext())
                return run;
        } return null;
    }

    /**
     * Returns the first element in the list (will always be the root element)
     * @return The Join object of the first item
     */
    private Join<T> first() {
        return root;
    }

    /**
     * Iterates over the list and performs an action on every item from begin to end
     * @param action the action being performed
     */
    public void forEach(Consumer<? super T> action) {
        for (var run = first(); run != null; run = run.getNext())
            action.accept(run.getElement());
    }

    /**
     * Iterates over the list and performs an action on every item from end to start
     * @param action the action being performed
     */
    public void forContrary(Consumer<? super T> action) {
        for (var run = last(); run != null; run = run.getPrev())
            action.accept(run.getElement());
    }

    /**
     * Iterates over the list and performs an action on every join from begin to end.
     * A join allows you to get the next and previous element in the list
     * @param action the action being performed
     */
    public void forEachJoin(Consumer<? super Join<T>> action) {
        for (var run = first(); run != null; run = run.getNext())
            action.accept(run);
    }

    /**
     * Iterates over the list and performs an action on every join from end to start.
     * A join allows you to get the next and previous element in the list
     * @param action the action being performed
     */
    public void forContraryJoin(Consumer<? super Join<T>> action) {
        for (var run = last(); run != null; run = run.getPrev())
            action.accept(run);
    }

    /**
     * Filters the current list by a predicate
     * @param predicate the predicate to check the items in the list against
     * @return a new list only consisting of the elements that passed the check
     */
    public JoinedList<T> filter(Predicate<? super T> predicate) {
        JoinedList<T> filteredList = new JoinedList<>();
        for (var run = first(); run != null; run = run.getNext())
            if (predicate.test(run.getElement()))
                filteredList.append(run.getElement());

        return filteredList;
    }
}
