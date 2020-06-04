package maestro.exceptions;

public class UnknownEntityException extends CustomNotValidException {

    private final Class<?> clazz;
    private final long entityId;

    public UnknownEntityException(Class<?> clazz, long entityId) {
        super("NotExist", clazz.getSimpleName(), "id");
        this.clazz = clazz;
        this.entityId = entityId;
    }

    public String getEntityType() {
        return clazz.getSimpleName();
    }

    public long getEntityId() {
        return entityId;
    }
}