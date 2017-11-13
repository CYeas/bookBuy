说明：
1: model是我们是设定的4个小类，测试的时候你不用看了。
2: controller是我们的控制器，你也不用看了，只用调用就好了。
3: 因为我把view写在了controller里面了，所以你只要调用我写的controller就好了。
4: 我们的mvc架构就算搭建好了。
5: test里面有两个test，一个是他来测试model的正确性，一个是我用来测试controller的正确性。
你需要做的就是使用我的controller进行测试。
测试的步骤：
1：你必须要在数据库里中signup一些人员，这时候就要用到lib里面的sqlite的jar包，要在IDEA中add as library.这个初始化数据库和signup，你可以参考陈业写的test文件夹中的DataBasetest。
2: 然后就可以使用我的controller进行测试了，直接调用就好了，可以参考test文件夹中的controllertest。
3:特别注意，测试按照controller显示的步骤进行就可以了。