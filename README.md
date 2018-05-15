### PKI基础设施


### 模块简介

+ `ca-business` 功能

    + 功能模块，证书保存，密钥备份和恢复，证书作废和处理系统
    
+ `client-business` 功能
    + 用户模块，证书申请，加密，解密，签名功能，验证CA根证书的有效性功能

### 运行

+ 打包 `mvn clean package -Dmaven.test.skip=true`

+ 输出到`dist` 目录下，解压发布包，脚本添加可执行权限

+ 配置`application.properties` 文件

+ 运行`start.sh` 启动脚本

### 项目说明

+ 核心功能均位于control包下，功能实现依赖于service
+ 服务实现位于service包下
