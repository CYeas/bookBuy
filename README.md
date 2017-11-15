#购书系统


###已经发现的问题：
####controller类的bug
1. createbook方法中，step1和step2粘连
2. 不能循环添加书籍
3. 书籍列表显示会报错 listAllBook()完全不能用且重复，User类已经实现了GetAllBook方法
4. LogIn时没有调用User的isType方法和isAvaliable方法，导致代码冗余
5. GetBook方法没有加入选项，导致功能丢失，也无法测试
6. 缺乏条件查询书籍功能

####Book类的bug
1. 没有做出输入限制
2. 买书的数量可以输入任意值，包括负数

####User类的bug
1. 当一个不存在的用户登陆时，报错

