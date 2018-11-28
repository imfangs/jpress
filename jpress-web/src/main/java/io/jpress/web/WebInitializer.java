/**
 * Copyright (c) 2016-2019, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.web;

import com.jfinal.template.Engine;
import io.jboot.server.listener.JbootAppListenerBase;
import io.jpress.web.sharekit.MainKits;
import io.jpress.web.sharekit.PermissionKits;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: JPress 初始化工具
 * @Package io.jpress
 */
public class WebInitializer extends JbootAppListenerBase {


    @Override
    public void onJfinalEngineConfig(Engine engine) {

        engine.addSharedFunction("/WEB-INF/views/admin/_layout/_layout.html");
        engine.addSharedFunction("/WEB-INF/views/admin/_layout/_layer.html");
        engine.addSharedFunction("/WEB-INF/views/admin/_layout/_paginate.html");
        engine.addSharedFunction("/WEB-INF/views/ucenter/_layout/_layout.html");

        engine.addSharedStaticMethod(MainKits.class);
        engine.addSharedStaticMethod(PermissionKits.class);

    }


    @Override
    public void onJFinalStarted() {

        OptionInitializer.me().init();

    }

}
