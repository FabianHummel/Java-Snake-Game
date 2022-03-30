package snakegame.library;

public class Join<T> {
    private Join<T> prev;
    private Join<T> next;
    private T element;

    public Join(Join<T> prev, Join<T> next, T element) {
        this.prev = prev;
        this.next = next;
        this.element = element;
    }

    public Join<T> getPrev() {
        return prev;
    }

    public void setPrev(Join<T> prev) {
        this.prev = prev;
    }

    public Join<T> getNext() {
        return next;
    }

    public void setNext(Join<T> next) {
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public boolean hasNext() {
        return getNext() != null;
    }

    public boolean hasPrev() {
        return getPrev() != null;
    }
}
