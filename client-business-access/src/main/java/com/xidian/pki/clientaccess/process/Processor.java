package com.xidian.pki.clientaccess.process;

import java.util.List;

/**
 * Author: LvLiuWei
 * Created: 2017/9/20.
 */
public interface Processor<T> {

    void process(List<T> list);
}
