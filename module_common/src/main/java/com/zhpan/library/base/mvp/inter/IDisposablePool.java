package com.zhpan.library.base.mvp.inter;

import io.reactivex.disposables.Disposable;

/**
 * Description : 连接池
 */
public interface IDisposablePool {
    /**
     * rxjava管理订阅者
     * @param disposable
     */
    void addDisposable(Disposable disposable);

    /**
     * 丢弃连接 在view销毁时调用,取消订阅关系
     */
    void clearPool();

}
