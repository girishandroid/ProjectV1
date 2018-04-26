package com.girish.libraryv1.Constants;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.View;


/**
 * Created by shiri on 07-06-2017.
 */

public class StartFragment {
    int FADE_DEFAULT_TIME =500;
    int MOVE_DEFAULT_TIME =500;
    public StartFragment(FragmentManager manager, int container, Fragment fragment, View anmi, boolean backstack, Bundle b){
        Fragment currentFrag = manager.findFragmentById(container);
        boolean fragmentPopped = manager.popBackStackImmediate(fragment.getClass().getName(), 0);
        if (currentFrag != null && currentFrag.getClass().getName().equalsIgnoreCase(fragment.getClass().getName())) {
            return;
        }
        Fragment previousFragment = manager.findFragmentById(container);
        FragmentTransaction ft = manager.beginTransaction();
        if (!fragmentPopped) {

            if (b != null) {
                fragment.setArguments(b);
            }



            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // 1. Exit for Previous Fragment
                Fade exitFade = new Fade();
                exitFade.setDuration(FADE_DEFAULT_TIME);
                previousFragment.setExitTransition(exitFade);

                TransitionSet enterTransitionSet = new TransitionSet();
                enterTransitionSet.addTransition(TransitionInflater.from(previousFragment.getActivity()).inflateTransition(android.R.transition.move));
                enterTransitionSet.setDuration(MOVE_DEFAULT_TIME);
                enterTransitionSet.setStartDelay(0);
                fragment.setSharedElementEnterTransition(enterTransitionSet);

                Fade enterFade = new Fade();
                enterFade.setStartDelay(MOVE_DEFAULT_TIME);
                enterFade.setDuration(FADE_DEFAULT_TIME);

                fragment.setEnterTransition(enterFade);
                ft.addSharedElement(anmi, anmi.getTransitionName());
            }*/
            //  ft.setCustomAnimations(R.animator.fade_in_right, R.animator.fade_out_left, R.animator.fade_in_left, R.animator.fade_out_right);
            //   ft.hide(previousFragment).replace(container, fragment).show(fragment);
            //if(!backstack)
            ft.replace(container, fragment);
//                else
//                    ft.add(container, fragment);

            if(backstack)
                ft.addToBackStack(fragment.getClass().getName());
            ft.commit();

        }
    }
    public StartFragment(FragmentManager manager, int container, Fragment fragment, boolean backstack, Bundle b){
        Fragment currentFrag = manager.findFragmentById(container);
        boolean fragmentPopped = manager.popBackStackImmediate(fragment.getClass().getName(), 0);
        if (currentFrag != null && currentFrag.getClass().getName().equalsIgnoreCase(fragment.getClass().getName())) {
            return;
        }
        FragmentTransaction ft = manager.beginTransaction();
        if (!fragmentPopped) {

            if (b != null) {
                fragment.setArguments(b);
            }
            try{
                Fragment previousFragment = manager.findFragmentById(container);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // 1. Exit for Previous Fragment
                    /*Fade exitFade = new Fade();
                    exitFade.setDuration(FADE_DEFAULT_TIME);
                    previousFragment.setExitTransition(exitFade);

                    TransitionSet enterTransitionSet = new TransitionSet();
                    enterTransitionSet.addTransition(TransitionInflater.from(previousFragment.getActivity()).inflateTransition(android.R.transition.move));
                    enterTransitionSet.setDuration(MOVE_DEFAULT_TIME);
                    enterTransitionSet.setStartDelay(0);
                    fragment.setSharedElementEnterTransition(enterTransitionSet);

                    Fade enterFade = new Fade();
                    enterFade.setStartDelay(MOVE_DEFAULT_TIME);
                    enterFade.setDuration(FADE_DEFAULT_TIME);

                    fragment.setEnterTransition(enterFade);*/
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
            //  ft.setCustomAnimations(R.animator.fade_in_right, R.animator.fade_out_left, R.animator.fade_in_left, R.animator.fade_out_right);
            //   ft.hide(previousFragment).replace(container, fragment).show(fragment);
            //if(!backstack)
            ft.replace(container, fragment);
//            else
//                ft.add(container, fragment);

            if(backstack)
                ft.addToBackStack(fragment.getClass().getName());
            ft.commit();

        }
    }

    public StartFragment(FragmentManager manager, int container, Fragment fragment, boolean backstack, Bundle b,boolean isReplace){
        Fragment currentFrag = manager.findFragmentById(container);
        boolean fragmentPopped = manager.popBackStackImmediate(fragment.getClass().getName(), 0);
        if (currentFrag != null && currentFrag.getClass().getName().equalsIgnoreCase(fragment.getClass().getName())) {
            return;
        }
        FragmentTransaction ft = manager.beginTransaction();
        if (true) {

            if (b != null) {
                fragment.setArguments(b);
            }
            try{
                Fragment previousFragment = manager.findFragmentById(container);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // 1. Exit for Previous Fragment
                    Fade exitFade = new Fade();
                    exitFade.setDuration(FADE_DEFAULT_TIME);
                    previousFragment.setExitTransition(exitFade);

                    TransitionSet enterTransitionSet = new TransitionSet();
                    enterTransitionSet.addTransition(TransitionInflater.from(previousFragment.getActivity()).inflateTransition(android.R.transition.move));
                    enterTransitionSet.setDuration(MOVE_DEFAULT_TIME);
                    enterTransitionSet.setStartDelay(0);
                    fragment.setSharedElementEnterTransition(enterTransitionSet);

                    Fade enterFade = new Fade();
                    enterFade.setStartDelay(MOVE_DEFAULT_TIME);
                    enterFade.setDuration(FADE_DEFAULT_TIME);

                    fragment.setEnterTransition(enterFade);
                }*/

            }
            catch (Exception e){
                e.printStackTrace();
            }
            //  ft.setCustomAnimations(R.animator.fade_in_right, R.animator.fade_out_left, R.animator.fade_in_left, R.animator.fade_out_right);
            //   ft.hide(previousFragment).replace(container, fragment).show(fragment);


            //ft.add(R.id.containerMainActivity, fragment);
            ft.replace(container, fragment);
            //else
                //ft.add(container, fragment);

            ft.commit();

        }
    }

    public StartFragment(FragmentManager manager,int container, Fragment fragment, Bundle b){


        Fragment currentFrag = manager.findFragmentById(container);
        boolean fragmentPopped = manager.popBackStackImmediate(fragment.getClass().getName(), 0);
        if (currentFrag != null && currentFrag.getClass().getName().equalsIgnoreCase(fragment.getClass().getName())) {
            return;
        }
        for(int i = 0; i < manager.getBackStackEntryCount(); ++i) {
            manager.popBackStack();
        }
        FragmentTransaction ft = manager.beginTransaction();
        if (!fragmentPopped) {

            if (b != null) {
                fragment.setArguments(b);
            }
          //  ft.setCustomAnimations(R.animator.fade_in_right, R.animator.fade_out_left, R.animator.fade_in_left, R.animator.fade_out_right);
            ft.replace(container, fragment);
            ft.commit();
        }
    }
    public StartFragment(FragmentManager manager,int container, Fragment fragment, Bundle b, Boolean replace){
        boolean fragmentPopped = manager.popBackStackImmediate(fragment.getClass().getName(), 0);
        FragmentTransaction ft = manager.beginTransaction();
        if (!fragmentPopped) {
            if (b != null) {
                fragment.setArguments(b);
            }
            //ft.setCustomAnimations(R.animator.fade_in_right, R.animator.fade_out_left, R.animator.fade_in_left, R.animator.fade_out_right);
            ft.replace(container, fragment);
            ft.commit();
        }
    }

    public StartFragment(FragmentManager manager,int container, Fragment fragment, Bundle b, String tag) {
        Fragment currentFrag = manager.findFragmentById(container);
        boolean fragmentPopped = manager.popBackStackImmediate(fragment.getClass().getName(), 0);
        if (currentFrag != null && currentFrag.getClass().getName().equalsIgnoreCase(fragment.getClass().getName())) {
            return;
        }
        for(int i = 0; i < manager.getBackStackEntryCount(); ++i) {
            manager.popBackStack();
        }
        FragmentTransaction ft = manager.beginTransaction();
        if (!fragmentPopped) {

            if (b != null) {
                fragment.setArguments(b);
            }
            //ft.setCustomAnimations(R.animator.fade_in_right, R.animator.fade_out_left, R.animator.fade_in_left, R.animator.fade_out_right);
            ft.replace(container, fragment,tag);
            ft.commit();
        }
    }
}
