package ru.job4j.mytests;

public interface BankStore<T extends BankBase>  {
    void eat(T thing);
}
