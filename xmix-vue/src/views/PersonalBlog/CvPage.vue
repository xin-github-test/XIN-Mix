<template>
<!--  开始做页面-->
  <div class="wrapper">
    <div class="header">
      <div class="header-left" @click="$router.back()">
        <span>Go</span>
        <span>Back</span>
      </div>
      <span class="title">Welcome to my Blog!</span>
    </div>
    <div class="blog-content">
      <div class="blog-aside">
        <div class="blog-aside-header">
          <img class="left" :src="avatarUrl?avatarUrl:require('/src/assets/avatar1.jpg')" alt="">
          <div class="right">
            <div style="height: 50%;width: 100%;display: flex" class="top">
              <span class="name">{{ blogInfo.name }}</span>
              <span class="edu-background" style="margin-left: 40px">学历:</span>
              <span class="edu" style="padding-left: 5px">{{ blogInfo.educations.at(-1).education }}</span>
            </div>
            <div style="height: 50%;width: 100%;" class="bottom">
              <span>码龄{{ blogInfo.codeAge }}年</span>
              <span class="location" >地区:</span>
              <span class="address" style="padding-left: 5px">{{ blogInfo.address }}</span>
            </div>
          </div>
        </div>
        <div class="blog-aside-content">
          <div class="content-block">
            <span class="block-label">个人简介:</span>
            <span class="block-content">{{ blogInfo.profile }}</span>
          </div>
          <div class="content-block">
            <span class="block-label">求职意向:</span>
            <span class="block-content">{{ blogInfo.jobIntention }}</span>
          </div>
          <div class="content-block">
            <div class="block-label">教育背景:</div>
            <div class="block-content-education">
              <div class="education" v-for="edu in blogInfo.educations" :key="edu.id">
                <div class="schoolInfo">
                  <span class="info" style="color: #feedce">{{ edu.education }}:</span>
                  <span class="info">{{ edu.schoolName }}</span>
                  <span class="info">{{ edu.time }}</span>
                </div>
                <div class="majorInfo">
                  <span class="info">专业:</span>
                  <span class="info">{{edu.major}}</span>
                </div>
              </div>

            </div>
          </div>
          <div class="content-block">
            <span class="block-label">个人技能:</span>
            <span class="block-content">
              <template v-for="skill in blogInfo.skills">
                <span class="title" style="color: #feedce">{{ Object.keys(skill)[0] }}相关</span>
                <ul>
                  <li v-for="item in skill[Object.keys(skill)[0]]">
                    <div class="skill" style="display: flex">
                      <span class="name">{{ item.split(':')[0] }}</span>
                      <el-rate
                          disabled :value="Number(item.split(':')[1])"
                          text-color="#ff9900"
                      ></el-rate>
                    </div>
                  </li>
                </ul>
              </template>

            </span>
          </div>
        </div>
      </div>
      <div class="blog-cv-display">
        <div class="display-left">
          <div class="display-left-top">
            <div class="top-header">
              简历展示
            </div>
            <div style="margin-top: 5px" class="top-middle">
              <span class="slogan"> Show your CV there!</span>
              <button @click="getDefaultCv">Show/Hidden</button>
            </div>
            <div class="top-footer">
              <div class="top">
                <el-upload
                    :action="uploadUrl"
                    :before-upload="beforeUpload"
                    :on-success="handleRes"
                    style="margin-left: 15px;margin-right: auto"
                >
                  <el-button icon="el-icon-folder-opened"
                             size="small">
                    Upload your Cv!
                  </el-button>
                </el-upload>
                <el-button icon="el-icon-download"
                           v-show="isDisplay"
                           size="small">
                  Download
                </el-button>
              </div>
              <div class="bottom" v-show="isDisplay">
                <button style="margin: 0 auto" @click="curPage > 1?curPage--:curPage">
                  <i class="el-icon-caret-left"></i>
                  Pre
                </button>
                <button style="margin: 0 auto">
                  {{ curPage }}/{{ pageNum }}
                </button>
                <button style="margin: 0 auto" @click="curPage < pageNum? curPage++:curPage">
                  Next
                  <i class="el-icon-caret-right"></i>
                </button>
              </div>
            </div>
          </div>
          <div class="display-left-bottom">
              <pdf
                  class="pdf-view"
                  v-show="isDisplay"
                  :src="pdfSrc"
                  :page="curPage"
              >
              </pdf>
              <span class="display-slogan"
                    v-if="!isDisplay"
              >
                什么都没做，就已经
                <TimeSelf></TimeSelf>
              </span>
          </div>
        </div>
        <div class="display-right">
          <div class="review-title">评论区</div>
          <div class="review-content">
            <div class="comments">
              <div class="avatarBox">
                <img :src="require('/src/assets/avatar1.jpg')"/>
              </div>
              <div class="commentContent">
                <div class="author">Tom</div>
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                <div class="otherInfo">
                  <span class="time">2024-1-1 18:42</span>
                  <span class="reply" @click="isReply = !isReply">回复</span>
                  <span class="view-others-comments">
                    共有100条评论,<span class="view" @click="isShowReplys = !isShowReplys">请查看</span>
                  </span>
                </div>
                <div class="replys" v-show="isShowReplys">
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="comments">
              <div class="avatarBox">
                <img :src="require('/src/assets/avatar1.jpg')"/>
              </div>
              <div class="commentContent">
                <div class="author">Tom</div>
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                <div class="otherInfo">
                  <span class="time">2024-1-1 18:42</span>
                  <span class="reply" @click="isReply = !isReply">回复</span>
                  <span class="view-others-comments">
                    共有100条评论,<span class="view" @click="isShowReplys = !isShowReplys">请查看</span>
                  </span>
                </div>
                <div class="replys" v-show="isShowReplys">
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="comments">
              <div class="avatarBox">
                <img :src="require('/src/assets/avatar1.jpg')"/>
              </div>
              <div class="commentContent">
                <div class="author">Tom</div>
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                <div class="otherInfo">
                  <span class="time">2024-1-1 18:42</span>
                  <span class="reply" @click="isReply = !isReply">回复</span>
                  <span class="view-others-comments">
                    共有100条评论,<span class="view" @click="isShowReplys = !isShowReplys">请查看</span>
                  </span>
                </div>
                <div class="replys" v-show="isShowReplys">
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="comments">
              <div class="avatarBox">
                <img :src="require('/src/assets/avatar1.jpg')"/>
              </div>
              <div class="commentContent">
                <div class="author">Tom</div>
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                <div class="otherInfo">
                  <span class="time">2024-1-1 18:42</span>
                  <span class="reply" @click="isReply = !isReply">回复</span>
                  <span class="view-others-comments">
                    共有100条评论,<span class="view" @click="isShowReplys = !isShowReplys">请查看</span>
                  </span>
                </div>
                <div class="replys" v-show="isShowReplys">
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="comments">
              <div class="avatarBox">
                <img :src="require('/src/assets/avatar1.jpg')"/>
              </div>
              <div class="commentContent">
                <div class="author">Tom</div>
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                <div class="otherInfo">
                  <span class="time">2024-1-1 18:42</span>
                  <span class="reply" @click="isReply = !isReply">回复</span>
                  <span class="view-others-comments">
                    共有100条评论,<span class="view" @click="isShowReplys = !isShowReplys">请查看</span>
                  </span>
                </div>
                <div class="replys" v-show="isShowReplys">
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="comments">
              <div class="avatarBox">
                <img :src="require('/src/assets/avatar1.jpg')"/>
              </div>
              <div class="commentContent">
                <div class="author">Tom</div>
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                <div class="otherInfo">
                  <span class="time">2024-1-1 18:42</span>
                  <span class="reply" @click="isReply = !isReply">回复</span>
                  <span class="view-others-comments">
                    共有100条评论,<span class="view" @click="isShowReplys = !isShowReplys">请查看</span>
                  </span>
                </div>
                <div class="replys" v-show="isShowReplys">
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                  <div class="reply-comments">
                    <div class="reply-avatarBox">
                      <img :src="require('/src/assets/avatar1.jpg')"/>
                    </div>
                    <div class="commentContent">
                      <div class="reply-author">Tom&nbsp;@&nbsp;<span class="reply-to">Jerry</span></div>
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！
                      <div class="otherInfo">
                        <span class="time">2024-1-1 18:42</span>
                        <span class="reply" @click="isReply = !isReply">回复</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="input-comment" v-show="isReply">
            <textarea v-model="comments" placeholder="请输入评论，仅限80字符"></textarea>
            <div class="words-limit">{{ wordsNum }}/80</div>

            <button class="button1" @click="clearComments">清空
              <span></span>
              <span></span>
              <span></span>
              <span></span>
            </button>
            <button class="button2">发表
              <span></span>
              <span></span>
              <span></span>
              <span></span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import pdf from 'vue-pdf'
import {serverIp, serverPort} from "../../../public/config";
import TimeSelf from "@/components/TimeSelf.vue";
export default {
  name: "CvPage",
  components:{
    pdf,TimeSelf
  },
  created() {
    //1.获取blog的基本信息(若是其它用户登陆了，且自己做了blog后显示用户自己)
    const hasBlog = localStorage.getItem("roleInfo")?JSON.parse(localStorage.getItem("roleInfo")).hasBlog:false
    let roleId = 1
    if(hasBlog){
      roleId = JSON.parse(localStorage.getItem("roleInfo")).id
    }
    this.getBlogBaseInfo(roleId)
  },
  data(){
    return{
      avatarUrl: localStorage.getItem("roleInfo")?JSON.parse(localStorage.getItem("roleInfo")).avatarUrl:'',
      //展示pdf的当前页
      curPage: 1,
      //pdf的总页数
      pageNum: '',
      // pdfSrc: '/Files/cvPdf.pdf'
      pdfSrc: '',
      isNotDefaultCv: localStorage.getItem("roleInfo"), //是否获取默认的简历
      isDisplay:false,
      //文件上传url
      uploadUrl: localStorage.getItem("roleInfo")?'http://'+serverIp+':'+serverPort+'/manager/file/upload/'+JSON.parse(localStorage.getItem("roleInfo")).id:'http://'+serverIp+':'+serverPort+'/manager/file/upload/',
      //展示用户信息的对象
      blogInfo:{
        educations:[
          {
            education:''
          },
        ]
      },
      //用户评论
      comments:'',
      isReply: false,
      isShowReplys: false

    }
  },
  //计算属性
  computed:{
    wordsNum(){
      //TODO 没有区分中英文（中英文以及符号一共只能输入80字）
      let length = this.comments.length;
      if(length >= 80){
        this.comments = this.comments.slice(0,79)
        this.$message.error("字数超出限制！")
      }
      return length >= 80?80:length;
    }
  },
  methods:{
    //获取博客的基本信息
    getBlogBaseInfo(roleId){
      this.request.get("/blog/info/"+roleId).then(
          res => {
            if(res.code === 200){
              this.blogInfo = res.data
            }
          }
      )
    },
    //文件上传之前的操作
    beforeUpload(file){
      const isPDF = file.type === 'application/pdf'
      const isLogin = localStorage.getItem("roleInfo")?JSON.parse(localStorage.getItem("roleInfo")):''
      if(!isPDF){
        this.$message.error("请上传pdf文件！")
      }
      if(!isLogin){
        this.$message.error("请先登陆！")
        this.$router.push("/login")
      }
      return isPDF && isLogin;
    },
    //文件上传成功后，对结果进行处理
    handleRes(res){
      if(res.code === 200){
        this.$message.success("文件上传成功！")
      }else {
        this.$message.error("文件上传失败！")
      }
    },
    getCvByUrl(url){
      //发送请求获取作者的简历  需要从blog服务中获取相应的数据
      this.request.get(url).then(
          res => {
            //获取默认的简历
            if (res.code === 200) {
              this.pageNum = 1
              this.pageNum = res.data.pageNum
              //通过文件名获取文件流
              this.pdfSrc = "http://localhost:88/manager/file/"+res.data.name
            }
          }
      )
    },
    getDefaultCv() {
      this.pdfSrc = ''
      this.isDisplay = !this.isDisplay
      let url = this.isNotDefaultCv?'/blog/blog/cv/'+JSON.parse(localStorage.getItem("roleInfo")).id:'/blog/blog/cv/default'
      if (this.isDisplay) {
        //发送请求获取作者的简历  需要从blog服务中获取相应的数据
        this.request.get(url).then(
            res => {
              //获取默认的简历
              if (res.code === 200 && res.data !== '-1') {
                this.pageNum = 1
                this.pageNum = res.data.pageNum
                //通过文件名获取文件流
                this.pdfSrc = "http://localhost:88/manager/file/"+res.data.name
              }else{
                this.getCvByUrl('/blog/blog/cv/default')
              }
            }
        )
     }
    },
    //清空输入的评论
    clearComments(){
      this.comments = ''
      this.isReply = false
    }
  }
}
</script>

<style scoped>
* {
  outline: none;
  box-sizing: border-box;
}
.wrapper{
  width: 100%;
}
::-webkit-scrollbar {
  width: 6px;
  border-radius: 10px;
}
::-webkit-scrollbar-thumb {
  background-color: rgb(21 20 26 / 63%);
  border-radius: 10px;
}
.header{
  width: 100%;
  height: 20%;
  display: flex;
  background-image: url("@/assets/CvPage/header0.svg");
  background-size: 100%;
  background-position-y: 95%;
  cursor: pointer;
  /*border: solid red;*/

}
.header .title{
  width: 100%;
  height: 150px;
  text-align: center;
  line-height: 150px;
  font-size: 80px;
  font-weight: 700;
  color: #1ee2e7;
  font-style: italic;
  text-shadow: 5px 5px 5px red;
  cursor: pointer;
  /*transition: .5s;*/
  animation: fliped 1.5s linear;
}
@keyframes fliped {
  0% {
    transform: rotateX(0deg);
  }
  50% {
    transform: rotateX(180deg);
  }
  100% {
    transform: rotateX(0deg);
  }
}
.header .title:hover{
  color: yellow;

  text-shadow: 5px 5px 5px #1ee2e7;
}
.header .header-left{
  width: 100px;
  display: flex;
  flex-direction: column;
  border-right: 1px #808191 solid;
  cursor: pointer;
  border-top-left-radius: 100px;
  border-bottom-left-radius: 100px;
  border-left: 5px #808191 solid;
}
.header .header-left:hover{
  color: #1ee2e7;
  font-weight: bold;
  text-shadow: 2px 2px 2px red;
  border-left-color: #1ee2e7;
}
.header-left span{
  flex-grow: 1;
  text-align: center;
  font-style: italic;
  font-size: 20px;
}
.header-left span:first-child{
  display: flex;
  align-items: center;
  justify-content: center;
}

.blog-content{
  display: flex;
  background-image: url("@/assets/CvPage/blog-cv-display.svg");
  background-size: cover;
  width: 100%;
  height: 100%;
}
.blog-content .blog-aside{
  display: flex;
  flex-direction: column;
  /*border-top: 2px black solid;*/
  width: 30%;
  height: 600px;
  overflow-y: auto;   /*设置x轴内容溢出的内容的处理方式：此处为，若内容溢出则提供滚轮*/
  overflow-x: hidden;
}
.blog-content .blog-aside .blog-aside-header{
  display: flex;
  height: 15%;
  background-image: url("@/assets/CvPage/blog-aside-header.svg");
  background-position: center;
  background-size: 150%;
  /*border-bottom: 2px white solid;*/
  position: sticky;
  top: 0;
  z-index: 2;    /*显示层级*/
}
.blog-content .blog-aside .blog-aside-header .left{
  width: 25%;
  object-fit: cover;
  border-radius: 50%;
}
.blog-content .blog-aside .blog-aside-header .right{
  display: flex;
  width: 80%;
  flex-direction: column;
}
.blog-content .blog-aside .blog-aside-header .right span{
  padding-left: 20px;
  line-height: 57px;
  font-size: 20px;
  font-family: 楷体,sans-serif;
  color: white;
}
.blog-aside-content{
  width: 100%;
  /*height: 100%;*/
  /*border: 2px white solid;*/
  display: flex;
  flex-direction: column;
}
.blog-aside-content .content-block{
  display: flex;
  width: 100%;
  margin-top: 15px;
  padding-bottom: 15px;
  border-bottom: 1px #808191 solid;
}
.blog-aside-content .content-block .block-label{
  width: 25%;
  /*color: #b6afe7;*/
  color: #e69a42;
  font-family: 楷体,sans-serif;
  font-size: 18px;

}
.blog-aside-content .content-block .block-content,
.blog-aside-content .content-block .block-content-education{
  width: 75%;
  display: flex;
  flex-direction: column;
  /*color: #e69a42;*/
  color: #fca60b;
  font-family: 楷体,sans-serif;
  font-size: 18px;
  font-weight: 600;

}
.blog-aside-content .content-block .block-content .title{
  margin-top: 5px;
  margin-bottom: 5px;
  font-size: 20px;
  font-style: italic;
}
.blog-aside-content .content-block .block-content ul .skill{
  display: flex;
  width: 100%;
}
.blog-aside-content .content-block .block-content ul .skill .name{
  width: 50%;
}
.blog-aside-content .content-block .block-content-education{
  width: 75%;
  display: flex;
  flex-direction: column;
}
.blog-aside-content .content-block .block-content-education .education{
  display: flex;
  flex-direction: column;
  width: 100%;
}
.education + .education{
  padding-top: 10px;
}
.blog-aside-content .content-block .block-content-education .education .schoolInfo,
.blog-aside-content .content-block .block-content-education .education .majorInfo{
  display: flex;
  width: 100%;
}
.blog-aside-content .content-block .block-content-education .education .schoolInfo .info,
.blog-aside-content .content-block .block-content-education .education .majorInfo .info{
  margin-right: 10px;
}
li + li{
  margin-top: 10px;
}
.blog-content .blog-cv-display{
  width: 70%;
  display: flex;
  /*overflow-y: auto;*/
}
.blog-content .blog-cv-display .display-left{
  width: 60%;
}
.blog-content .blog-cv-display .display-left .display-left-top{
  width: 100%;
  height: 20%;
  padding: 5px 12px;
  /*border: 2px white solid;*/
  display: flex;
  flex-direction: column;
  background-image: url("@/assets/CvPage/blog-aside-header.svg");
  background-size: 150%;
  opacity: 90%;
  box-shadow: 0 0 5px black,0 0 10px black,0 0 15px black;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-middle{
  width: 100%;
  display: flex;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-middle .slogan{
  width: 50%;
  margin-right: auto;
  font-family:  "Inter", sans-serif;
  font-style: italic;
  font-size: 20px;
  font-weight: 600;
  color: #1ee2e7;
  text-align: center;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-middle button{
  font-family: "Inter", sans-serif;
  padding-right: 5px;
  font-weight: 600;
  font-size: 20px;
  color: #fca60b;
  border: none;
  background: none;
  border-radius: 5px;
  cursor: pointer;
  font-style: italic;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-middle button:hover{
  background-color: #6c5ecf;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-header{
  width: 100%;
  text-align: center;
  font-family: "楷体",sans-serif;
  color: white;
  font-size: 30px;
  text-shadow: 0 0 5px white;
}
.blog-content .blog-cv-display .display-left .display-left-bottom{
  width: 100%;
  height: 60%;
  display: flex;
  justify-content: center;
  overflow-y: auto;
  /*border: 2px red solid;*/
  background-color: black;
  opacity: 90%;
  box-shadow:0 0 5px black, 0 0 10px black, 0 0 15px black;
}
.pdf-view{
  width: 100%;
  height: 100%;
}
.display-slogan{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #6c5ecf;
  font-family: "楷体", sans-serif;
  font-size: 60px;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-footer{
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 5px;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-footer .top{
  display: flex;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-footer .top button{
  font-family: "Inter", sans-serif;
  font-size: 20px;
  font-style: italic;
  background: none;
  border: none;
  color: #4DC3FA;
  border-radius: 10px;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-footer .top button:hover{
  background-color: #805625;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-footer .bottom{
  display: flex;
  width: 100%;
  margin-top: 5px;
}
.blog-content .blog-cv-display .display-left .display-left-top .top-footer .bottom button{
  background: none;
  border-radius: 5px;
  color: white;
  font-family: "Inter", sans-serif;
  font-size: 15px;
  border: none;
  font-style: italic;

}
.blog-content .blog-cv-display .display-left .display-left-top .top-footer .bottom button:not(:nth-child(2)):hover{
  cursor: pointer;
  color: #6c5ecf;
}
.blog-content .blog-cv-display .display-right{
  display: flex;
  flex-direction: column;
  width: 40%;
  height: 100%;
  /*border: 2px red solid;*/
  position: relative;
}
.blog-content .blog-cv-display .review-title{
  width: 100%;
  height: 74px;
  /*border: 2px white solid;*/
  text-align: center;
  line-height: 74px;
  color: white;
  font-size: 30px;
  font-family: "楷体", sans-serif;
  text-shadow: 0 0 5px white;
  background-image: url("@/assets/CvPage/blog-aside-header.svg");
  background-size: 150%;
  opacity: 80%;
}
.blog-content .blog-cv-display .review-content{
  width: 100%;
  height: 100%;
  /*border: 2px white solid;*/
  background-color: black;
  opacity: 90%;
  overflow-y: auto;
}
.blog-content .blog-cv-display .review-content .comments{
  display: flex;
}

.blog-content .blog-cv-display .review-content .comments .commentContent{
  padding: 5px;
  display: flex;
  flex-direction: column;
}
.blog-content .blog-cv-display .review-content .comments .commentContent .replys .reply-comments{
  display: flex;
}
.blog-content .blog-cv-display .review-content .comments .commentContent .replys .reply-comments .reply-avatarBox img{
  width: 30px;
  height: 30px;
  border-radius: 15px;
}
.blog-content .blog-cv-display .review-content .comments .commentContent .author{
  color: white;
}
.blog-content .blog-cv-display .review-content .comments .commentContent .otherInfo{
  display: flex;
  gap: 10px;
}
.blog-content .blog-cv-display .review-content .comments .commentContent .otherInfo .time{
  color: #57606f;
  font-size: 12px;
}
.blog-content .blog-cv-display .review-content .comments .commentContent .otherInfo .reply{
  color: #8c939d;
  font-size: 12px;
}
.blog-content .blog-cv-display .review-content .comments .commentContent .otherInfo .reply:hover{
  color: yellow;
  cursor: pointer;
}
.blog-content .blog-cv-display .review-content .comments .commentContent .otherInfo .view-others-comments {
  color: #8c939d;
  font-size: 12px;
}
.blog-content .blog-cv-display .review-content .comments .commentContent .otherInfo .view-others-comments .view:hover{
  cursor: pointer;
  color: white;
}
.blog-content .blog-cv-display .review-content .avatarBox{
  float: left;
}
.blog-content .blog-cv-display .review-content .avatarBox img{
  width: 45px;
  height: 45px;
  border-radius: 20px;
  margin-top: 5px;
  margin-left: 5px;
}
.blog-content .blog-cv-display .input-comment{
  width: 100%;
  height: 25%;
  /*border: 2px red solid;*/
  position: absolute;
  right: 0;
  bottom: 145px;
  background-color: black;
  /*opacity: 80%;*/
}
.blog-content .blog-cv-display .input-comment textarea{
  width: 100%;
  height: 128px;
  background: linear-gradient(
      to right,
      hsl(152, 97%, 59%),
      hsl(300, 100%, 50%)
  );
  -webkit-background-clip: text;
  color: transparent;
  font-family: "楷体", sans-serif;
  font-size: 20px;   /*85个字*/
  font-weight: bold;
  transition: filter 800ms;
  word-break:break-all;
  word-wrap: break-word; /*保持中英文正常的换行*/
  resize: none;  /*禁止拖动*/
  caret-color: #1ee2e7;
}
.blog-content .blog-cv-display .words-limit{
  width: 50px;
  height: 25px;
  /*border: 2px red solid;*/
  font-size: 15px;
  position: absolute;
  right: 0;
  bottom: 55px;
  text-align: right;
  padding-right: 2px;
}
.blog-content .blog-cv-display .input-comment button{
  width: 70px;
  height: 30px;
  border-radius: 8px;
  font-family: "楷体", sans-serif;
  font-size: 18px;
  font-weight: bold;
  color: #03e9f4;
  cursor: pointer;
  background-color: black;
  border: none;
  display: inline-block;
  overflow: hidden;
  position: absolute;
  transition: .5s;
}
.blog-content .blog-cv-display .input-comment .button2{
  right: 5px;
  bottom: 20px;
}
.blog-content .blog-cv-display .input-comment .button1{
  left: 5px;
  bottom: 20px;
}
.blog-content .blog-cv-display .input-comment button:hover{
  background: #03e9f4;
  color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 5px #03e9f4,
  0 0 25px #03e9f4,
  0 0 50px #03e9f4,
  0 0 100px #03e9f4;
}
.blog-content .blog-cv-display .input-comment button span{
  /*border: 1px red solid;*/
  position: absolute;
  display: block;
}
.blog-content .blog-cv-display .input-comment button span:nth-child(1){
  top: 0;
  left: -100%;
  width: 100%;
  height: 1px;
  background: linear-gradient(90deg, transparent, #03e9f4);
  animation: btn-anim1 1s linear infinite;
}
@keyframes btn-anim1 {
  0% {
    left: -100%;
  }
  50%,100% {
    left: 100%;
  }
}
.blog-content .blog-cv-display .input-comment button span:nth-child(2){
  right: 0;
  top: -100%;
  width: 1px;
  height: 100%;
  background: linear-gradient(180deg, transparent, #03e9f4);
  animation: btn-anim2 1s linear infinite;
  animation-delay: .25s
}
@keyframes btn-anim2 {
  0% {
    top: -100%;
  }
  50%,100% {
    top: 100%;
  }
}
.blog-content .blog-cv-display .input-comment button span:nth-child(3){
  bottom: 0;
  right: -100%;
  width: 100%;
  height: 1px;
  background: linear-gradient(270deg, transparent, #03e9f4);
  animation: btn-anim3 1s linear infinite;
  animation-delay: .5s
}
@keyframes btn-anim3 {
  0% {
    right: -100%;
  }
  50%,100% {
    right: 100%;
  }
}
.blog-content .blog-cv-display .input-comment button span:nth-child(4){
  left: 0;
  bottom: -100%;
  width: 1px;
  height: 100%;
  background: linear-gradient(360deg, transparent, #03e9f4);
  animation: btn-anim4 1s linear infinite;
  animation-delay: .75s
}
@keyframes btn-anim4 {
  0% {
    bottom: -100%;
  }
  50%,100% {
    bottom: 100%;
  }
}
</style>