# algorithm-java
用java描述的数字结构与算法

运行
~~~
//Enter the directory
cd com/assignment

//Compile all files
javac -d . *.java

//Customize commands and allocate memory
alias cryptoGraph="java -Xss64m com/assignment/Main"

//To run the program
cryptoGraph
cryptoGraph -i
cryptoGraph -r assetFile tradeFile
~~~

量的介绍
该程序的主要目的是对读取文件进行数据分析。这个程序有三种模式。在第一种模式中，程序只输出一些提示消息。在第二种模式中，当命令行参数为-i时，将交互测试四种数据结构，即链表、图、排序和哈希表。在第三种模式下，即当命令行参数为-r时，程序的所有功能都以交互方式显示。通过使用四种数据结构，最终实现了所有功能


2安装

1、本程序没有依赖项，所以不需要添加任何依赖项，只需使用javac编译所有的。java文件即可

2、安装:

按照自述文件中的说明操作

3、未来工作:

直接序列化链表会消耗大量内存。最后，可能会发生堆栈溢出。也许可以找到更好的序列化数据的方法。

使所有的数据结构更通用，而不是为某个需求做大的更改

所有的数据结构和算法都在相应的测试类中进行了充分的测试，在交互测试模式下可以看到测试结果。并能参与测试过程。在程序中，总共使用了四种数据结构，并对所有四种数据结构进行了测试

4 UML

![UML](https://user-images.githubusercontent.com/63355034/139312891-422d2c48-d0ff-4f1c-998d-58d14f1f9749.png)

5类描述

AssetCell:该类的目的是作为资产文件中的数据的模板，并使用该类生成的对象在资产文件中存储每一段数据。因为数据可以通过使用对象来组织

TradeCell:该类的目的是作为交易文件中的数据的模板，并使用该类生成的对象在交易文件中存储每一块数据。因为数据可以通过使用对象来组织

DSAGraph:这个类的目的是创建一个图表来表示各种虚拟货币之间的联系。因为只有通过图表的数据结构，我们才能找到两种货币之间的所有交易渠道

DSAHashtable:该类的目的是创建一个哈希表，并将数据导入到哈希表中进行搜索。由于哈希表的搜索效率非常高，遍历链表的时间复杂度为o(n)，哈希表的搜索复杂度为o(1)。由于导入的数据非常大，使用遍历链表进行搜索非常困难。效率低下。

DSAAdvancedSort:该类的目的是使用高级排序算法对一些数据进行排序。这里，我使用快速排序，因为快速排序是目前最好的排序算法，它的时间复杂度只有o (nlogn)

DSALinkedList:该类的目的是导入数据。在这里，数组不用于导入数据的原因是我们不确定文件中有多少数据，所以我们不能确定数组的大小。可以动态扩展链表的大小。此外，问题中的一些操作涉及删除操作。链表的删除操作比数组更方便，时间复杂度更低。链表删除操作的时间复杂度仅为o(1)，而数组删除操作的时间复杂度为o(m)

FileOp:该类的目的是执行文件操作，因为问题涉及大量的文件操作。显然，将所有文件操作封装到一个类中是非常方便和清晰的。

Main:该类的目的是作为程序的主类存在。在这个类中，调用其他类来完成所有工作。每个函数都封装在一个函数中，使程序非常清楚

UnitTestDSAGraph:目的是测试DSAGraph类

UnitTestDSAHashtable:目的是测试DSAHashtable类

unittestdssort:目的是测试dssort类

UnitTestDSALinkedList:目的是测试DSALinkedList类









