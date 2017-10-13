/*
Copyright 2015 Zilla Chen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package zilla.libcore.ui;

import pub.zilla.logzilla.Log;

/**
 * LayoutInjectUtil
 * Created by zilla on 15/5/25.
 */
public class LayoutInjectUtil {
    /**
     * get id of layout
     *
     * @param container the container of InjectLayout
     * @return the layout id
     */
    public static int getInjectLayoutId(Object container) {
        InjectLayout injectLayout = container.getClass().getAnnotation(InjectLayout.class);
        if (injectLayout == null) {
            Log.d("Can not find annotation 'InjectLayout' on " + container.getClass().getName() + ".");
            return 0;
        }
        return injectLayout.value();
    }

    /**
     * get id of menu
     *
     * @param container
     * @return
     */
    public static int getInjectMenuId(Object container) {
        InjectLayout injectLayout = container.getClass().getAnnotation(InjectLayout.class);
        if (injectLayout == null) {
            Log.d("Can not find annotation 'InjectLayout' on " + container.getClass().getName() + ".");
            return 0;
        }
        return injectLayout.menu();
    }
}
