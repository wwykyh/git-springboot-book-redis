/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2018-08-07 15:52:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `AdminId` int(11) NOT NULL AUTO_INCREMENT,
  `AdminName` varchar(12) DEFAULT NULL,
  `AdminPassword` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`AdminId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123456');

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `BookId` int(11) NOT NULL AUTO_INCREMENT,
  `BooktypeId` int(11) DEFAULT NULL,
  `BookName` varchar(25) DEFAULT NULL,
  `BookAuthor` varchar(25) DEFAULT NULL,
  `BookEdition` varchar(25) DEFAULT NULL,
  `BookPrice` int(11) DEFAULT NULL,
  `BookAmount` int(11) DEFAULT NULL,
  `BookIntroduce` varchar(1000) DEFAULT NULL,
  `IssuanceDate` date DEFAULT NULL,
  `BookImages` varchar(25) DEFAULT NULL,
  `bookspecil` int(11) DEFAULT NULL,
  `bookdiscount` float DEFAULT NULL,
  PRIMARY KEY (`BookId`),
  KEY `fk_book_category` (`BooktypeId`),
  CONSTRAINT `fk_book_category` FOREIGN KEY (`BooktypeId`) REFERENCES `category` (`TypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '1', '高等数学', '同济大学数学系', '高等教育出版社', '31', '5', '本书是同济大学数学系编的《高等数学》第七版，从整体上说与第六版没有大的变化，内容深广度符合“工科类本科数学基础课程教学基本要求”，适合高等院校工科类各专业学生使用。本书分上、下两册出版，下册包括向量代数与空间解析几何、多元函数微分法及其应用、重积分、曲线积分与曲面积分、无穷级数等内容，书末还附有习题答案与提示。', '2018-07-31', '1_1.png', '0', '1');
INSERT INTO `book` VALUES ('2', '1', '加密与解密', '段钢', '电子工业出版社', '158', '3', '本书以加密与解密为切入点，讲述了软件安全领域的基础知识和技能，如调试技能、逆向分析、加密保护、外壳开发、虚拟机设计等。这些知识彼此联系，读者在掌握这些内容之后，很容易就能在漏洞分析、安全编程、病毒分析、软件保护等领域进行扩展。从就业的角度来说，掌握加密与解密的相关技术，可以提高自身的竞争能力；从个人成长的角度来说，研究软件安全技术有助于掌握一些系统底层知识，是提升职业技能的重要途径。作为一名合格的程序员，除了掌握需求分析、设计模式等外，如果能掌握一些系统底层知识、熟悉整个系统的底层结构，在工作中必将获益良多。', '2018-07-31', '1_2.png', '0', '0.9');
INSERT INTO `book` VALUES ('3', '2', '面具', '王小枪', '北京联合出版有限公司', '72', '4', '1948年腊月初一，潜伏在哈尔滨市公安局的国民党特务李春秋被突然唤醒，上级要求他即刻制造炸弹，协助配合绝密的“黑虎计划”。\r\n 潜伏了十年的李春秋已经快遗忘了自己的使命，拥有幸福平凡生活的他不愿抛妻弃子，开始想尽办法逃脱保密局的控制。\r\n 除了要对付残忍冷酷的昔日战友，李春秋还在受着现在同事、侦察科丁战国的怀疑和试探。内外交困的李春秋压力重重。\r\n 最终，故事中的两个主人公李春秋和丁战国，这一对戴着不同面具的对手，在这一年的除夕夜，走向了不同的命运结局……', '2018-07-31', '2_1.png', '0', '0.8');
INSERT INTO `book` VALUES ('4', '3', '北大往事', '橡子、谷行', '北京联合出版有限公司', '42', '5', '《北大往事》精选北大70、80、90年代富有才华与个性的师生关于北大生活的精彩文章49篇，通过对幽微往事的追寻，试图探索北大人丰饶鲜活的内心图谱。\r\n本书曾于1998年北大百年校庆之际推出首版，大受欢迎，洛阳纸贵，被誉为民间书写北大故事的代表性作品。此后一版再版。此次再版，以2008年新世界出版社出版的《北大往事（珍藏版）》为底，新增数篇文章，构成更为精彩完整的版本，并与《北大读本》《北大百年新诗》一起作为“北大典藏”系列同时推出，共同呈现出一个完整的北大图谱，以献礼北大建校120周年。', '2018-07-31', '3_1.png', '1', '0.7');
INSERT INTO `book` VALUES ('5', '4', '学会管自己', '陈梦敏', '海豚出版社', '80', '3', '这套童话语言优美，故事精彩，想象奇特，并配有大量精美手绘插图，是基础阅读和文学启蒙的上选。该书系统地归纳了孩子从幼儿园大班到小学中低年级这段时间里会面临的主要问题，帮助学龄前和小学低年级学生在独立品格、学习能力、社会适应三方面应对小学新环境，让孩子顺利完成对外部环境变化的适应和自我角色的转变，在富有文学性的故事中，引导孩子学会认真不马虎，学会勤快不拖延，不找借口不搪塞，坚持自我不盲从，发现优点不自卑，学会坚持不放弃……共10册，4-6岁亲子共读，7-9岁独立阅读。', '2014-08-22', '4_1.png', '1', '0.6');
INSERT INTO `book` VALUES ('6', '5', '毛泽东论中国历史人物', '盛巽昌', '上海书店出版社', '151', '5', '盛巽昌先生经多年研究，将*同志对中国历史人物的评论，采撷、编制成本书，力图通过通俗易懂却又严谨的文字，让广大读者从一个侧面来深化认知这位在中国历史上具有深刻影响的伟大的人民领袖。', '2018-07-31', '5_1.png', '0', '0.5');
INSERT INTO `book` VALUES ('7', '6', '伟大的中国工业革命', '文一', '清华大学出版社', '44', '3', '中国崛起无疑是人类历史上自英国工业革命以来*为壮观的全球性历史事件。而当代西方主流经济学理论，尤其是关于经济发展的制度经济学理论，却无力解释中国的崛起。作者认为只有彻底重新阐释被制度经济学所误读的工业革命和西方崛起的历史，才能真正解释中国的增长奇迹，以及为什么中国义无反顾的崛起是不可阻挡的。与此同时，中国从一个贫穷积弱的农业社会向工业超级大国的惊人转型所蕴含的“发展政治经济学”逻辑，不仅从一个侧面深刻揭示了当代西方主流经济学的根本缺陷，而且也为非洲难以逃离的贫困陷阱，拉丁美洲失去的年华和不断滋生的债务危机，19世纪的大分流和欧洲的异军突起，以及貌似神秘的英国工业革命本身提供了新的更加深刻的注释。', '2018-07-31', '6_1.png', '1', '0.4');
INSERT INTO `book` VALUES ('8', '7', '格局', '何权峰', '江苏文艺出版社', '21', '4', '格局指一个人的眼界、胸襟、胆识等心理要素的内在布局。再大的烙饼也大不过烙它的锅。只会盯着树皮里的虫子不放的鸟儿是不可能飞到白云之上的，只有眼里和心中装满了山河天地的雄鹰才能自由地在天地间翱翔！\r\n其实人生的格局就在你怎么看自己，在你所认识的人，在你说的每句话，在你给人的感觉，在你做事的态度，在你经历的遭遇，在你的每个念头，在你的所作所为。本书共有48个思考题，帮你更深层地了解自己，从而改变思维方式，调整处事格局。', '2018-07-31', '7_1.png', '0', '0.3');
INSERT INTO `book` VALUES ('9', '8', ' 只有时间知道', '蕾拉小姐', '北京联合出版有限公司', '26', '6', '这本书，关于时间和旅行。\r\n　　旅程没有终点，因为它正是你的整个人生。\r\n　　时间浩大，为了对抗我们的渺小，唯有步履不停。\r\n　　我庆幸自己有足够的好奇，去迎接颠覆，去面对未知。\r\n　　在旅程中，新的人生拼图，正在一片一片完整起来。\r\n　　每一次出发，都是从原本的生活抽离，\r\n　　去往他人的生活里、空间里、故事里，\r\n　　成为一个观察者、捕猎者、参与者，等待发生，也与发生交织。', '2018-07-31', '8_1.png', '1', '0.2');
INSERT INTO `book` VALUES ('10', '9', 'Java从入门到精通', '明日科技', '清华大学出版社', '54', '8', '《Java从入门到精通（第4版）》从初学者角度出发，通过通俗易懂的语言、丰富多彩的实例，详细介绍了使用Java语言进行程序开发需要掌握的知识。全书分为28章，包括初识Java，熟悉Eclipse开发工具，Java语言基础，流程控制，字符串，数组，类和对象，包装类，数字处理类，接口、继承与多态，类的高级特性，异常处理，Swing程序设计，集合类，I/O（输入/输出），反射，枚举类型与泛型，多线程，网络通信，数据库操作，Swing表格组件，Swing树组件，Swing其他高级组件，高级布局管理器，高级事件处理，AWT绘图与音频播放，打印技术和企业进销存管理系统等。书中所有知识都结合具体实例进行介绍，涉及的程序代码给出了详细的注释，可以使读者轻松领会Java程序开发的精髓，快速提高开发技能', '2018-07-31', '9_1.png', '0', '0.1');

-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `car_Id` int(11) NOT NULL AUTO_INCREMENT,
  `car_Userid` int(11) DEFAULT NULL,
  `car_Bookid` int(11) DEFAULT NULL,
  `car_Images` varchar(12) DEFAULT NULL,
  `car_Bookname` varchar(20) DEFAULT NULL,
  `car_Price` float DEFAULT NULL,
  `car_Number` int(11) DEFAULT NULL,
  `car_Total` float DEFAULT NULL,
  PRIMARY KEY (`car_Id`),
  KEY `fk_car_users` (`car_Userid`),
  KEY `fk_car_book` (`car_Bookid`),
  CONSTRAINT `fk_car_book` FOREIGN KEY (`car_Bookid`) REFERENCES `book` (`BookId`),
  CONSTRAINT `fk_car_users` FOREIGN KEY (`car_Userid`) REFERENCES `users` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('1', '3', '4', '3_1.png', '北大往事', '29.4', '3', '88.2');
INSERT INTO `car` VALUES ('2', '4', '1', '1_1.png', '高等数学', '31', '5', '155');
INSERT INTO `car` VALUES ('6', '3', '2', '1_2.png', '加密与解密', '142.2', '1', '142.2');
INSERT INTO `car` VALUES ('16', '2', '4', '3_1.png', '北大往事', '29.4', '1', '29.4');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `TypeId` int(11) NOT NULL AUTO_INCREMENT,
  `TypeName` varchar(25) DEFAULT NULL,
  `TypeIntroduce` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '教育', '关于教育书籍');
INSERT INTO `category` VALUES ('2', '小说', '小说书籍');
INSERT INTO `category` VALUES ('3', '文艺', '文艺书籍');
INSERT INTO `category` VALUES ('4', '童书', '儿童读物');
INSERT INTO `category` VALUES ('5', '人文社科', '人文社科书籍');
INSERT INTO `category` VALUES ('6', '经管', '经管书籍');
INSERT INTO `category` VALUES ('7', '成功/励志', '成功/励志书籍');
INSERT INTO `category` VALUES ('8', '生活', '生活书籍');
INSERT INTO `category` VALUES ('9', '科技', '科技书籍');

-- ----------------------------
-- Table structure for `messages`
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `MessageId` int(11) NOT NULL AUTO_INCREMENT,
  `MessageName` varchar(20) DEFAULT NULL,
  `MessageEmail` varchar(32) DEFAULT NULL,
  `MessagePhone` varchar(11) DEFAULT NULL,
  `MessageAddress` varchar(32) DEFAULT NULL,
  `Message` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`MessageId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of messages
-- ----------------------------
INSERT INTO `messages` VALUES ('7', '测试', 'text@text.text', 'text', 'texttext', '一二三四五六七');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `OrderID` int(11) NOT NULL,
  `BookID` int(11) DEFAULT NULL,
  `BookCount` int(11) DEFAULT NULL,
  `BookPrice` varchar(50) DEFAULT NULL,
  `CustomerName` varchar(30) DEFAULT NULL,
  `OrderTime` datetime DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `fk_orders_book` (`BookID`),
  KEY `fk_orders_users` (`CustomerName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(8) DEFAULT NULL,
  `PassWord` varchar(12) DEFAULT NULL,
  `UserEmail` varchar(20) DEFAULT NULL,
  `UserPhone` varchar(11) DEFAULT NULL,
  `UserAdress` varchar(50) DEFAULT NULL,
  `UserDate` date DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `UserName` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('2', 'admin1', 'a123456', '455918029@qq.com', '15060606220', '福建厦门', '2018-08-01');
INSERT INTO `users` VALUES ('3', 'zzf1', 'aa147852', '455918029@qq.com', '15060606220', '福建厦门', '2018-08-01');
INSERT INTO `users` VALUES ('4', 'zzf1', 'aa147852', '455918029@qq.com', '15060606220', '福建厦门', '2018-08-01');
INSERT INTO `users` VALUES ('5', 'zf11', 'qq123456', '454545@qq.com', '15060606220', 'ggggg', '2018-08-01');
