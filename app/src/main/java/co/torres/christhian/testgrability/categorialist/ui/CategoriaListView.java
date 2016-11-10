package co.torres.christhian.testgrability.categorialist.ui;

import java.util.List;

import co.torres.christhian.testgrability.models.CategoryApp;

/**
 * Created by Ceindetec02 on 07/11/2016.
 */

public interface CategoriaListView {

    void loadDataSuccess(List<CategoryApp> categoryAppList);

    void loadDataError(String errorMessage);


}
