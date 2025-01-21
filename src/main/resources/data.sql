-- Lecture 表（课程表）
INSERT INTO lecture (id, title, description, uploader_id) VALUES
(1, 'Python入门教程', '学习Python编程的基础知识。', 1),
(2, 'Java高级教程', '深入探讨Java的高级概念和框架。', 1),
(3, '数据库管理', '学习关系型数据库和SQL语句。', 1);

-- Video 表（视频表）
INSERT INTO video (id, lecture_id, title, transcript, path,cover_path) VALUES
(1, 1, 'Python基础知识1', '欢迎学习Python编程！本节课介绍变量。', '/video/122452656_da2-1-16.mp4','/img/cover_1.jpg'),
(2, 1, 'Python基础知识2', '欢迎学习Python编程！本节课介绍数据类型。', '/video/122452656_da2-1-16.mp4','/img/cover_1.jpg'),
(3, 2, 'Java高级概述1', '本节课深入探讨Java的核心概念。', '/video/122452656_da2-1-16.mp4','/img/cover_1.jpg'),
(4, 2, 'Java高级概述2', '本节课深入探讨Java的核心概念。', '/video/122452656_da2-1-16.mp4','/img/cover_1.jpg'),
(5, 3, 'SQL入门', '学习SQL的基础语法，包括SELECT语句的使用。', '/video/122452656_da2-1-16.mp4','/img/cover_1.jpg'),
(6, 3, 'SQL进阶', '学习SQL的高级语法，包括JOIN语句的使用。', '/video/122452656_da2-1-16.mp4','/img/cover_1.jpg');

-- Note 表（笔记表）
INSERT INTO note (id, description, img_path, video_id) VALUES
(1, '第1章笔记：变量与数据类型', '/img/db56877bf758e92f813c25705b6d5fc0eed20512.jpg', 1),
(2, 'Java高级教程笔记：多线程与并发', '/img/db56877bf758e92f813c25705b6d5fc0eed20512.jpg', 2),
(3, '数据库基础笔记：主键和外键', '/img/db56877bf758e92f813c25705b6d5fc0eed20512.jpg', 3);

-- User 表（用户表）
INSERT INTO user (id, username, password, salt, display_name) VALUES
(1, 'chenzhihu', '$2a$10$THH81g1z6la5SZ9sxSZdauUEjvuPClkdaLwiVI97kxLL4i5oItKdW', '', '陈志虎'),
(2, 'yangjialin', '$2a$10$THH81g1z6la5SZ9sxSZdauUEjvuPClkdaLwiVI97kxLL4i5oItKdW', '', '杨佳林');

-- Role 表（角色表）
INSERT INTO role (id, name) VALUES
(1, 'ROLE_STUDENT');

-- User_Collection 表（用户收藏表）
INSERT INTO user_collection (id, user_id, lecture_id) VALUES
(1, 1, 1), -- 陈志虎收藏了Python课程
(2, 1, 2), -- 陈志虎收藏了Java课程
(3, 2, 1); -- 杨佳林收藏了Python课程

-- User_Lecture 表（用户与课程关系表）
INSERT INTO user_lecture (id, user_id, lecture_id) VALUES
(1, 1, 1), -- 陈志虎上传了Python课程
(2, 102, 2); -- 杨佳林上传了Java课程

-- User_Role 表（用户角色表）
INSERT INTO user_role (id, user_id, role_id) VALUES
(1, 1, 1), -- 陈志虎是学生
(2, 2, 1); -- 杨佳林是学生


