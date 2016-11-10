package co.torres.christhian.testgrability.appdetail;

import android.content.Context;

/**
 * Created by Ceindetec02 on 09/11/2016.
 */

public class AppDetailInteractorImpl implements AppDetailInteractor {

    private AppDetailRepository appDetailRepository;

    public AppDetailInteractorImpl() {

        this.appDetailRepository = new AppDetailRepositoryImpl();

    }

    @Override
    public void doLoadData(Context context, int idApp) {
        appDetailRepository.loadData(context, idApp);
    }
}
