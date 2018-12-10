package ru.alvisid.pacs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.model.abstractions.AbstractId;
import ru.alvisid.pacs.model.abstractions.HasId;
import ru.alvisid.pacs.repository.TypicalRepository;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

public abstract class AbstractService<T extends TypicalRepository<S>, S extends HasId>
        implements TypicalService<S> {
    protected final T repository;

    public AbstractService(T repository) {
        this.repository = repository;
    }

    @Override
    public S create(S obj) {
        Assert.notNull(obj, "employee must not be null");
        return repository.save(obj);
    }

    @Override
    public void update(S obj) throws NotFoundException {
        Assert.notNull(obj, "employee must not be null");
        checkNotFoundWithId(repository.save(obj), obj.getId());
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public S get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<S> getAll() {
        return repository.getAll();
    }
}
