package article.domain.co;

public abstract class CommandObject {
    public long actorId;
    public CommandObject() {}
    public abstract Long verifyCOSecurity();


}
