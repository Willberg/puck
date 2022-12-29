package spi;

import java.util.List;

/**
 * 核心思想: 搜索接口
 *
 * @author bill
 * @since 2022/12/29 14:57
 */
public interface Search {
    List<String> searchDoc(String keyword);
}
