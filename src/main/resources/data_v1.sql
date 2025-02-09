-- Lecture 表（课程表）
INSERT INTO lecture (id, title, description, uploader_id) VALUES
(1, 'CS50 哈佛大学 计算机科学导论 名校公开课【合集·完结】', 'CS50是哈佛大学校内以及 MOOC 平台 edX 上学习人数最多的计算机课程，内容包括：C语言，数据结构、算法，Python (Flask)，前后端网站开发等。', 1),
(2, '【浙江大学】Java入门与进阶 翁恺', 'https://mooc.study.163.com/course/ 翁恺老师的精品课之一', 1),
(3, '《高等数学》同济版 2024年更新|宋浩老师', '《高等数学》同济版 宋浩老师 新浪微博：宋浩老师_ice_mouse；下册开始更新新视频了，在专辑的最底部，跟随课堂进度录制。', 2);

-- Video 表（视频表）
INSERT INTO video (id, lecture_id, title, transcript, path, cover_path) VALUES
(1, 1, '第0周 Scratch 图形编程', '', '/video/11fed637fe754cf69c7a77173972de61.mp4','/img/67042ae5c3a86de9a30d999eeade92f026f0a644.jpg'),
(2, 1, '第1周 C 语言',           '', '/video/21fed637fe754cf69c7a77173972de61.mp4','/img/67042ae5c3a86de9a30d999eeade92f026f0a644.jpg'),

(3, 2, '【零基础】1.1 计算机与编程语言', '', '/video/31fed637fe754cf69c7a77173972de61.mp4','/img/c65b5780678482c890b633f8639246333984a9f3.png'),
(4, 2, '1.2 计算机的思维方式', '',          '/video/41fed637fe754cf69c7a77173972de61.mp4','/img/c65b5780678482c890b633f8639246333984a9f3.png'),
(5, 2, '1.3 准备Java编程软件', '',          '/video/51fed637fe754cf69c7a77173972de61.mp4','/img/c65b5780678482c890b633f8639246333984a9f3.png'),

(6, 3, '1.1 映射', '',          '/video/61fed637fe754cf69c7a77173972de61.mp4','/img/video/db56877bf758e92f813c25705b6d5fc0eed20512.jpg'),
(7, 3, '1.1 函数', '',          '/video/71fed637fe754cf69c7a77173972de61.mp4','/img/video/db56877bf758e92f813c25705b6d5fc0eed20512.jpg'),
(8, 3, '1.1 函数的几种特性', '', '/video/81fed637fe754cf69c7a77173972de61.mp4','/img/video/db56877bf758e92f813c25705b6d5fc0eed20512.jpg');

-- Note 表（笔记表）
INSERT INTO note (video_id, image_path, description, timestamp) VALUES
(1, '/image/c1f8d4fa86174a37b8c563c3c952346b.jpg', '图中人物正在用手比划，字幕显示他在说：“换句话说，我用我的手已经表示了这么多图样了。”这可能涉及手势或手部动作的表达方式。', 300),
(1, '/image/1473cf521b5f424dbac74fcbebfe6bf4.jpg', '当前PPT显示“representation”字样，可能涉及表示法或表示理论的内容。', 600),
(1, '/image/cf5039cb3bc94753b61ffb82956d9e85.jpg', '当前内容正在介绍“抽象”这一概念，屏幕上显示了“abstraction”一词。', 900),
(1, '/image/4274531e5c1d4f43b3bb24b4be0ed57e.jpg', '图中展示了一位演讲者在台上，背景是一幅雕像。字幕显示：“再加上最后一个字节，to form that pixel, and then one more byte,”，可能在讲解图像处理或数据结构相关内容。', 1200),
(1, '/image/f430c94917f749e8a3d41076f9438060.jpg', '当前内容涉及一个数列：2, 4, 6, 8, 10，提示可能是一个不完全的数列。', 1500),
(1, '/image/44b8b76ab5b6433caaac211f9aed1a94.jpg', '当前内容是关于如何简洁准确地表达自己的意图。', 1800),
(1, '/image/853a472d85fa4976af6fcbe83d0e8fca.jpg', '图中展示了一个动画场景，包含一个路灯和垃圾桶。字幕显示“是的，我很喜欢垃圾”，并附有英文翻译。这可能是在讲解与垃圾或环保相关的内容。', 2100),
(1, '/image/a3c17f9579ab4288b59cd263c7209e06.jpg', '图中展示了一个编程界面，左侧是代码块，右侧是舞台。代码块中有一个角色询问名字并等待回答，舞台显示一个角色说“Hello, world”。下方有角色“Snap”的设置选项。', 2400),
(1, '/image/8d21d45df80348e6b7ecdf169a08c082.jpg', 'PPT上显示的是一个软件界面，包含多个窗口和图标。界面中有一个图表，可能用于展示数据或分析结果。整体布局简洁，信息清晰。', 2700),
(1, '/image/dbdf9a1c1ef645499129bbc803d7c496.jpg', '图中展示了一个编程界面，包含“touching”和“mousepos”两个模块，以及“play sound”和“figure”模块。字幕提到“是或否，真或假，1或0的答案”。', 3000),
(1, '/image/f74b1812ed3840b7a3b7fe942c739f5b.jpg', '图中展示了一个编程界面，左侧是代码块，右侧是舞台。代码块包括角色移动、旋转和重复执行等指令，舞台显示一个角色。字幕显示“现在，他依旧走得很快”。', 3300),
(1, '/image/62c9850d06a24788aabac29dea4c8c0f.jpg', '图中展示了一个编程界面，左侧是代码块，右侧是舞台。字幕显示“蓝色人偶有一个很简单的区块”。', 3600),
(1, '/image/d54e7448c95f48ad8d8995ea807bb29b.jpg', '当前内容涉及高级编程语言，如C语言和JavaScript。屏幕上显示的是一个编程环境，可能在讲解代码或编程概念。', 3900),
(1, '/image/957777d4bd714745a40a9293e44f8b85.jpg', 'PPT上展示了多个大学的标志，包括耶鲁、麻省理工、斯坦福、普林斯顿、宾夕法尼亚大学等。旁边有文字提示“Break it down”，可能在分析这些大学的某些特征或排名。', 4200),

(2, '/image/07974ee9fed141ba813ce536c7b1c0e6.jpg', '图中显示的是一个演讲场景，讲台上有一位演讲者，背景屏幕上展示了一张图表，图中有一条红线。字幕显示“回想一下这根红线”，可能在讨论某个重要数据或趋势。', 300),
(2, '/image/08fee6be63fb4ba5b4d37416d21bfdb7.jpg', '图中没有板书或PPT内容。字幕显示：“但其实我们做的也没错 but we did it correctly arguably.”', 600),
(2, '/image/8f93404fc85f41f49fafb71eb956201f.jpg', '板书列出了多个编程命令，如“print”、“if”、“else”等，可能在讲解编程基础或逻辑结构。', 900),
(2, '/image/bc5f402472114cb682a4c0da514b7579.jpg', '图中展示了两种语言的“Hello, World”示例：一种类似自然语言的简单命令“say hello, world”，另一种是C语言代码“printf("hello, world\n");”。字幕提到这类似于英语或其他语言中的句号。', 1200),
(2, '/image/60cc4b2f7dba4c8c853df552f91fdd63.jpg', '板书展示了一段C语言代码，包含一个if语句，用于比较变量x和y的大小。如果x小于y，则输出“x is less than y”。', 1500),
(2, '/image/4c099fb61f894c97b7e8c0646449dc07.jpg', '当前内容是关于将信息并列排列。', 1800),
(2, '/image/4923508e1026441f997010319c20d83b.jpg', '当前PPT展示了一个Python代码示例，代码中包含一个函数定义，函数名为`printTwice`，用于打印两次传入的字符串。', 2100),
(2, '/image/099de0c95dbd4de198a93214ba5fe215.jpg', '屏幕上显示了一段代码，用于打印数字1到10。代码包含一个for循环，循环变量为i，范围从1到10。', 2400),
(2, '/image/72928ee669b04163b38eab672998e178.jpg', '图中展示了Scratch编程中的代码块，包括“询问并等待”和“说”功能。通过“join”代码块将“hello”和用户回答连接，实现对话功能。字幕提到在C语言中也使用了类似的“join”代码块。', 2700),
(2, '/image/fdd30252a0ee466fa6ebfb75ea754453.jpg', '图中展示了一个C语言代码示例，代码中包含`#include <stdio.h>`和`int main(void)`函数。下方终端显示了代码的输出结果“hello, world\n”。', 3000),
(2, '/image/6e4fee7add6448d6a093736611867c3c.jpg', '当前内容描述：演讲者正在讨论他现在可以访问的服务器权限。', 3300),
(2, '/image/beec668a629442bca332743052896153.jpg', '屏幕上显示的是C语言代码示例，包含`#include <stdio.h>`和`int main()`函数，使用`printf`输出“hello, world”。终端窗口展示了编译和运行命令。', 3600),
(2, '/image/e345a0b97b584db29d1156cc419a0025.jpg', '屏幕上显示的是C++代码结构，包括变量声明、函数定义和循环结构。代码涉及变量初始化、函数调用和条件判断。', 3900),
(2, '/image/b4e5a141bb114a829f61d1d40a07ab18.jpg', '代码示例展示了一个简单的C程序，用于获取用户输入并输出问候语。程序包含头文件`<cs50.h>`和`<stdio.h>`，使用`get_string`函数获取输入，并通过`printf`输出结果。终端显示编译和运行命令。', 4200),
(2, '/image/87f631c60403403dbbf841fd4a2eb617.jpg', '图中没有显示板书或PPT内容。', 4500),
(2, '/image/86bc4a715a184905bdd55a7095d3fa0c.jpg', '代码示例展示了如何使用`get_int`函数获取用户输入的整数，并用`printf`输出。字幕提到“就像我们给Brian传递一个参数一样”，可能在讲解函数参数传递的概念。', 4800),
(2, '/image/e95b0851d9b34b449bef85bfab11ec2c.jpg', '代码示例展示了如何从用户获取两个整数并进行基本算术运算。包括加法、减法、乘法和除法，使用`printf`函数输出结果。', 5100),
(2, '/image/9aa037fa33274a87af3e07b8450f633a.jpg', '代码示例展示了如何从用户获取浮点数输入并进行除法运算。使用`get_float`函数获取变量`x`和`y`，然后通过`printf`格式化输出结果，保留一位小数。', 5400),
(2, '/image/9e899af7051b401d8678e737ae67240e.jpg', '代码示例展示了如何进行除法运算，使用`printf`函数输出结果。字幕提到计算机系统在计算金额时忽略了分的零头。', 5700),
(2, '/image/81455c7404034c83b1dbb264ffc701f5.jpg', '代码示例展示了条件语句的使用。当变量x等于y时，输出“x is equal to y”。终端显示运行结果，x为2，y为10。字幕解释了变量赋值。', 6000),
(2, '/image/5a9ed84758594a619d4953cbef367a3c.jpg', '代码展示了一个循环调用`cough`函数，该函数不接受任何输入。字幕说明当前`cough`函数不需要输入。', 6300),
(2, '/image/865eda10236045778f033e1f08d073c2.jpg', '代码展示了一个简单的C程序，包含`main`函数和`cough`函数。`main`函数中循环调用`cough`函数三次，`cough`函数输出“cough”。终端显示编译和运行命令。', 6600),
(2, '/image/a14a07bdb39140e6a4484823876daf29.jpg', '代码示例：提示用户输入正整数。函数`get_positive_int`通过`do-while`循环不断获取用户输入，直到输入大于等于1的整数。', 6900),
(2, '/image/aee0c9ccdcb84549b6831349ee365dc5.jpg', '屏幕上显示数字“111”，字幕提到“对应1的列、对应2的列、对应4的列”。这可能是在讲解二进制或列优先存储相关内容。', 7200),
(2, '/image/ce51c159b3e7429099dc4fe9df857cba.jpg', '代码演示整数溢出问题。终端显示程序输出结果，每次输出值翻倍。字幕解释每次翻倍增加的量更多。', 7500),
(2, '/image/396e54a3d79b47818c2cf962830cb217.jpg', '图中展示了一位演讲者在讲台上，背景屏幕上显示了一幅游戏画面，画面中有三个角色。字幕提到“甘地在游戏中并不是一个和平的角色”。', 7800),

(3, '/image/2357c31501ee443aaf61cdcca9567c57.jpg', 'PPT标题为“计算机的语言”，展示了一段十六进制代码。', 300);
-- 其他视频的笔记暂时设置为空缺，以测试笔记空缺时的显示


-- Caption 表（存放字幕地址和视频对应信息）
INSERT INTO caption (id, video_id,language,path) VALUES
(1,1,'zh-CN', '/caption/11fed637fe754cf69c7a77173972de61.srt'),
(2,2,'zh-CN', '/caption/21fed637fe754cf69c7a77173972de61.srt'),
(3,2,'zh-CN', '/caption/31fed637fe754cf69c7a77173972de61.srt');

-- User 表（用户表）
INSERT INTO user (id, username, password, salt, display_name) VALUES
(1, 'chenzhihu', '$2a$10$THH81g1z6la5SZ9sxSZdauUEjvuPClkdaLwiVI97kxLL4i5oItKdW', '', '陈志虎'),
(2, 'yangjialin', '$2a$10$THH81g1z6la5SZ9sxSZdauUEjvuPClkdaLwiVI97kxLL4i5oItKdW', '', '杨佳林'),
(3, 'video_processor', '$2a$10$THH81g1z6la5SZ9sxSZdauUEjvuPClkdaLwiVI97kxLL4i5oItKdW', '', 'video_processor');

-- Role 表（角色表）
INSERT INTO role (id, name) VALUES
(1, 'ROLE_STUDENT'),
(2, 'ROLE_VIDEO_PROCESSOR');

-- User_Role 表（用户角色表）
INSERT INTO user_role (id, user_id, role_id) VALUES
(1, 1, 1), -- 陈志虎是学生
(2, 2, 1), -- 杨佳林是学生
(3, 3, 2); -- video_processor是视频处理器

-- User_Collection 表（用户收藏表）
INSERT INTO user_collection (id, user_id, lecture_id) VALUES
(1, 1, 1), -- 陈志虎收藏了CS50
(2, 1, 2), -- 陈志虎收藏了Java课程
(3, 2, 1); -- 杨佳林收藏了CS50

-- User_Lecture 表（用户与课程关系表）   *弃用
-- INSERT INTO user_lecture (id, user_id, lecture_id) VALUES
