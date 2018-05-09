package yonky.yiqi.base;

/**
 * Created by Administrator on 2018/5/9.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
