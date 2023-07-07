package demo4.recovery;

public interface ICaretaker  {

    void loadBakFile();

    void saveBakFile();
    void recovery();

    void setMemento(Memento memento);
}
