## Machine Learning
1. Linear regression
2. Logistic regression
3. Linear classification
4. Naive Bayse
5. Support Vector Machine
## Deep Learning
**Other**
1. Batch Norm
2. Softmax

1. Transfer learning
    1. 数据少时可以冻结前面的layer，只自己重建softmax并训练；
    2. 数据越多可以冻结更少的layer，并自己重建后面的layer;
2. Data augmentation
    通过mirroring, random cropping, rotation, local wrapping, color shifting等制造更多训练数据，增强模型的鲁棒性。

**Basic Model**
1. RNN
    + Naive RNN
        - one memory cell
    + LSTM
        - one memory cell
        - Input Gate
        - Output Gate
        - Forget Gate
        - 三个门分别受三个信号控制，因此一个lstm单元相当于4个input
    + GRU
1. CNN
    + Conv
        - kernel/filter size
        - padding
        - stike
    + Pooling
        - Max Pooling
        - average Pooling
    + Sofamax
    
1. ResNet(残差网络)
    + Residual Block
        1. 包含 main path 和 shortcut
        2. shortcut 是否使用Conv分为 convolutional block 和 identity block;
        3. 为何有效：更容易刻画恒等式
        4. 1*1卷积的作用：相当于全连接层作用于不同位置，可以缩小通道数量。
1. Inception network
    1. Inception model

## Application
**NLP:**

**Computer version:**
1. classification: 1 obj
1. Localization: 1 obj
1. Detection: (YOLO you only look once) multi obj
    + Landmark detection
    + Slide windows detection
        
        移动框裁剪，每个框进行全连接判定；
        Cost large；
    + Slide Windows detection with convNet: 一次输入整个图片
        - Y = (Pc, bx, by, bh, bw, c1,c2,c3)
            1. Pc: 是物体的概率，0,1；
            1. bx, by： 中心点相对于格子的坐标；
            1. bh, bw： 物体相对于格子的长和宽；
            1. c1,c2,c3: 物体属于哪一种类别；
        - IOU(Intersection over union)：= Size 交集/ Size 并集，一般 > 0.5 当做正确；
        - No Max suppression
        
            1. 从最大概率矩形框F开始，分别判断A~E与F的重叠度IOU是否大于某个设定的阈值;
            2. 假设B、D与F的重叠度超过阈值，那么就扔掉B、D；并标记第一个矩形框F，是我们保留下来的。
            3. 从剩下的矩形框A、C、E中，选择概率最大的E，然后判断E与A、C的重叠度，重叠度大于一定的阈值，那么就扔掉；并标记E是我们保留下来的第二个矩形框。
            4. 就这样一直重复，找到所有被保留下来的矩形框。
        - Anchor boxes(锚盒)
        
            Y = ((Pc, bx, by, bh, bw, c1,c2,c3)(Pc, bx, by, bh, bw, c1,c2,c3));
            可用于处理两个物体重叠;
            取出物体中心点所在的格子，和与 Anchor boxex IOU最大的格子
            
