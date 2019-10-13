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
package io.jpress.web.front;

import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.utils.StrUtil;
import io.jboot.web.controller.annotation.RequestMapping;
import io.jboot.web.validate.EmptyValidate;
import io.jboot.web.validate.Form;
import io.jpress.model.UserCart;
import io.jpress.service.UserAddressService;
import io.jpress.service.UserCartService;
import io.jpress.service.UserService;
import io.jpress.web.base.UcenterControllerBase;

import java.util.Set;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Package io.jpress.web
 */
@RequestMapping(value = "/ucenter/cart", viewPath = "/WEB-INF/views/ucenter")
public class CartController extends UcenterControllerBase {

    @Inject
    private UserService userService;

    @Inject
    private UserCartService cartService;

    @Inject
    private UserAddressService addressService;


    /**
     * 购物车
     */
    public void index() {
        Page<UserCart> page = cartService.paginate(1, 10);
        setAttr("page", page);
        render("cart.html");
    }

    /**
     * 选择某个
     */
    @EmptyValidate({
            @Form(name = "id",message = "id不能为空")
    })
    public void select() {
        Set<String> ids = StrUtil.splitToSet(getPara("id"), ",");

        for (String idvalue : ids) {
            UserCart cart = cartService.findById(idvalue);
            if (cart != null) {
                cart.setSelected(true);
                cartService.update(cart);
            }
        }

        renderOkJson();
    }

    /**
     * 取消选择
     */
    @EmptyValidate({
            @Form(name = "id",message = "id不能为空")
    })
    public void unselect() {
        Set<String> ids = StrUtil.splitToSet(getPara("id"), ",");
        for (String idvalue : ids) {
            UserCart cart = cartService.findById(idvalue);
            if (cart != null) {
                cart.setSelected(false);
                cartService.update(cart);
            }
        }

        renderOkJson();
    }

    /**
     * 对某个购物车商品 +1
     */
    @EmptyValidate({
            @Form(name = "id",message = "id不能为空")
    })
    public void addcount() {
        String id = getPara("id");
        cartService.doAddCountById(id);
        renderOkJson();
    }

    /**
     * 都某个购物车商品 -1
     */
    @EmptyValidate({
            @Form(name = "id",message = "id不能为空")
    })
    public void subtractcount() {
        String id = getPara("id");
        cartService.doSubtractCountById(id);
        renderOkJson();
    }


}