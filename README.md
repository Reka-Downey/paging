# 分页封装模型
## 用法
```java
		List<String> content = new ArrayList<>(Arrays.asList("Reka", "雷卡", "Rachel", "瑞秋"));
		// 通过 Page.custom(List<T>) 得到创建者，随后通过创建者模式构造分页数据
		Page<String> page = Page.custom(content)
                                .pageOffset(1)
                                .pageSize(5)
                                // 根据 pageOffset、pageSize、content.size()，totalElements 被调整成 9
                                .totalElements(8)
                                .pagingNavigationNum(3)
                                .build();

        List<User> userContent = new ArrayList<>();
        userContent.add(new User("Reka", 23));
        userContent.add(new User("雷卡", 23));
        userContent.add(new User("Rachel", 22));
        userContent.add(new User("瑞秋", 22));
        Page<User> userPage = new Page.Builder<>(userContent)
                // pageRequest 的优先级高于 pagingNavigationNum、pageOffset、pageSize，随后这三项的配置不起作用
                .pageRequest(new PageRequest(3, 4, 4))
                .pagingNavigationNum(5)
                .totalElements(30)
                .pageOffset(3)
                .pageSize(7)
                .build();
```
