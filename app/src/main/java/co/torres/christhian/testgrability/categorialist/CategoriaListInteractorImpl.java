package co.torres.christhian.testgrability.categorialist;

import android.content.Context;

public class CategoriaListInteractorImpl implements CategoriaListInteractor {


    private CategoriaListRepository categoriaListRepository;

    public CategoriaListInteractorImpl() {

        categoriaListRepository = new CategoriaListRepositoryImpl();

    }

    @Override
    public void doLoadData(Context context) {

        categoriaListRepository.loadData(context);

    }

}
