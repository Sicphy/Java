package sample;

public class DeleteController {
    private DeleteMethod deleteMethod;

    DeleteController(DeleteMethod deleteMethod) {
        this.deleteMethod = deleteMethod;
    }

    public void startMethod() {
        deleteMethod.sendRequest();
    }

    public void setFileName(String fileName) {
        deleteMethod.setFileName(fileName);
    }
}