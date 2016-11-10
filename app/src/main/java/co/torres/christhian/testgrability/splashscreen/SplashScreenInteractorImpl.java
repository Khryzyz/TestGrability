package co.torres.christhian.testgrability.splashscreen;

import android.content.Context;

import co.torres.christhian.testgrability.lib.VolleySingleton;

public class SplashScreenInteractorImpl implements SplashScreenInteractor {

    private SplashScreenRepository splashScreenRepository;

    public SplashScreenInteractorImpl() {

        splashScreenRepository = new SplashScreenRepositoryImpl();

    }

    @Override
    public void checkData(Context context) {

        splashScreenRepository.checkData(context);

    }

    @Override
    public void doUpdateData(Context context) {

        splashScreenRepository.updateData(context);

    }
}
