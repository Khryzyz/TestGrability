package co.torres.christhian.testgrability.models;

public class AppInfo {

    private int id;
    private String name;
    private String summary;
    private String price;
    private String currency;
    private String rights;
    private String title;
    private int categoryId;
    private String release;
    private String urlImageCero;
    private String urlImageUno;
    private String urlImageDos;

    public AppInfo() {
    }

    public AppInfo(int id, String name, String summary, String price, String currency, String rights, String title, int categoryId, String release, String urlImageCero, String urlImageUno, String urlImageDos) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.price = price;
        this.currency = currency;
        this.rights = rights;
        this.title = title;
        this.categoryId = categoryId;
        this.release = release;
        this.urlImageCero = urlImageCero;
        this.urlImageUno = urlImageUno;
        this.urlImageDos = urlImageDos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getUrlImageCero() {
        return urlImageCero;
    }

    public void setUrlImageCero(String urlImageCero) {
        this.urlImageCero = urlImageCero;
    }

    public String getUrlImageUno() {
        return urlImageUno;
    }

    public void setUrlImageUno(String urlImageUno) {
        this.urlImageUno = urlImageUno;
    }

    public String getUrlImageDos() {
        return urlImageDos;
    }

    public void setUrlImageDos(String urlImageDos) {
        this.urlImageDos = urlImageDos;
    }
}
