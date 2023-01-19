package repositories;

public abstract class RepositoryDecorator <T> implements Repository<T> {
    protected AbstractRepository<T> redisRepository;

    public RepositoryDecorator(AbstractRepository<T> repository) {
        this.redisRepository = repository;
    }
}
