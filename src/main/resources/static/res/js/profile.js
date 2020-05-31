function init_myself_series_btnGrp(elem,seriesId,modal,userId) {
    elem.find('.settingBtn').click(function () {
        let form = modal.find('.seriesInfoForm');
        form.children().remove();
        let form_seriesId_head = $('<h5></h5>').addClass('font-alt mb-0').text('Series ID:');
        let form_seriesId = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','seriesId').attr('type','text').attr('placeholder','Series Id').attr('readonly',''));

        let form_SeriesName_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_SeriesName_head = $('<h5></h5>').addClass('font-alt mb-0').text('Combine into other Series or Alter this Series name:');
        let form_SeriesName = $('<div></div>').addClass('row form-group');
        let seriesName_select =$('<div></div>').addClass('col-xs-5 col-sm-5').append($('<select></select>').addClass('form-control seriesIdSelector').attr('name','newSeriesId')
            .append($('<option></option>').attr('selected','').attr('disabled','').attr('value','').text('Choose...')));

        let seriesName_checkbox = $('<div></div>').addClass('col-xs-2 col-sm-2').append($('<label></label>').append($('<input>').attr('type','checkbox').addClass('seriesSwCheckbox')).append(' 改名'));
        let seriesName_input = $('<div></div>').addClass('col-xs-5 col-sm-5').append($('<input>').addClass('form-control').attr('name','newSeriesName').attr('placeholder','Alter series name').attr('type','text').attr('disabled',''));
        form_SeriesName.append(seriesName_select).append(seriesName_checkbox).append(seriesName_input);

        //
        // let form_alterSeriesName_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        // let form_alterSeriesName_head = $('<h5></h5>').addClass('font-alt mb-0').text('Series name:');
        // let form_alterSeriesName = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','seriesName').attr('type','text').attr('placeholder','Series name'));
        //

        let form_shortName_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_shortName_head = $('<h5></h5>').addClass('font-alt mb-0').text('Series shortName:');
        let form_shortName = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','seriesShortName').attr('type','text').attr('placeholder','Series shortNam'));

        let form_createTime_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_createTime_head = $('<h5></h5>').addClass('font-alt mb-0').text('Create time:');
        let form_createTime = $('<div></div>').addClass('form-group').append($('<p></p>').addClass('form-control').attr('name','seriesCreateTime').attr('type','text').attr('placeholder','Create time').attr('readonly',''));

        let form_updateTime_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_updateTime_head = $('<h5></h5>').addClass('font-alt mb-0').text('Update time:');
        let form_updateTime = $('<div></div>').addClass('form-group').append($('<p></p>').addClass('form-control').attr('name','seriesUpdateTime').attr('type','text').attr('placeholder','Update time').attr('readonly',''));

        let form_seriesIntro_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_seriesIntro_head = $('<h5></h5>').addClass('font-alt mb-0').text('Series Intro:');
        let form_seriesIntro = $('<div></div>').addClass('form-group').append($('<textarea></textarea>').addClass('form-control hTextarea').attr('name','seriesIntro').attr('rows','7').attr('cols','7').attr('maxlength','300').attr('placeholder','Series Intro'));

        let form_seriesStatus_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_seriesStatus_head = $('<h5></h5>').addClass('font-alt mb-0').text('Series Status:');
        let form_seriesStatus = $('<div></div>').addClass('form-group');
        let status_select = $('<select></select>').addClass('form-control').attr('name','seriesStatus')
            .append($('<option></option>').attr('selected','').attr('disabled','').attr('value','').text('Choose...'))
            .append($('<option></option>').attr('value','7').text('highlight'))
            .append($('<option></option>').attr('value','6').text('normal'))
            .append($('<option></option>').attr('value','5').text('friendly'))
            .append($('<option></option>').attr('value','4').text('private'))
            .append($('<option></option>').attr('value','3').text('draft'))
            .append($('<option></option>').attr('value','2').text('preview').attr('disabled',''))
            .append($('<option></option>').attr('value','1').text('pre-create').attr('disabled',''));
        form_seriesStatus.append(status_select).append($('<p></p>').addClass('help-block text-danger pull-right hInputInfo itemInfoModalWarning'));

        form.append(form_seriesId_head).append(form_seriesId).append(form_SeriesName_hr).append(form_SeriesName_head).append(form_SeriesName)
            .append(form_shortName_hr).append(form_shortName_head).append(form_shortName).append(form_createTime_hr).append(form_createTime_head).append(form_createTime)
            .append(form_updateTime_hr).append(form_updateTime_head).append(form_updateTime).append(form_seriesIntro_hr).append(form_seriesIntro_head).append(form_seriesIntro)
            .append(form_seriesStatus_hr).append(form_seriesStatus_head).append(form_seriesStatus);

        init_series_list(userId, $('.seriesIdSelector'), 'series',seriesId);

        $('.seriesSwCheckbox').click(function () {
            blog_modify_checkbox($(this),'newSeries');
        });
        modal.find('input[name*=seriesId]').val([[${blogSeries.seriesId}]]);
        modal.find('select[name*=newSeriesId]').val([[${blogSeries.seriesId}]]);
        modal.find('input[name*=seriesShortName]').val([[${blogSeries.seriesShortName}]]);
        modal.find('p[name*=seriesCreateTime]').text([[${#dates.format(blogSeries.seriesCreateTime, 'yyyy-MM-dd HH:mm')}]]);
        modal.find('p[name*=seriesUpdateTime]').text([[${#dates.format(blogSeries.seriesUpdateTime, 'yyyy-MM-dd HH:mm')}]]);
        modal.find('textarea[name*=seriesIntro]').val([[${blogSeries.seriesIntro}]]);
        modal.find('select[name*=seriesStatus]').val([[${blogSeries.seriesStatus}]]);
        modal.modal('show');
        modal.find('button.confirmBtn').click(function () {
            update_series(form);
        });
    });
    elem.find('.deleteBtn').click(function () {
        let form = modal.find('.seriesInfoForm');
        form.children().remove();
        form.append($('<h5></h5>').addClass('font-alt mb-0').text('确认删除此Series吗？(Id:'+[[${blogSeries.seriesId}]]+', Series name:'+[[${blogSeries.seriesName}]]+')'));
        modal.modal('show');
        modal.find('button.confirmBtn').click(function () {
            delete_series([[${blogSeries.seriesId}]]);
        });

    });

}

function update_series(form) {
    $.ajax({
        url: global_url_map.context_base+global_url_map.series_url,
        type: "PUT",
        data: form.serialize(),
        success: function (result) {
            console.log(result);
            if (result.code == 100) {
                toastr.success(result.msg);
                window.location.href = global_url_map.context_base + global_url_map.series_url+'/'+result.extend.newSeriesId;
            } else {
                toastr.error(result.msg);
            }
        },
        error:function () {
            toastr.error('更新失败');
        }
    });
}

function delete_series(seriesId) {
    $.ajax({
        url: global_url_map.context_base+global_url_map.series_url+'/'+seriesId,
        type: "DELETE",
        success: function (result) {
            console.log(result);
            if (result.code == 100) {
                toastr.success(result.msg);
                window.location.href = global_url_map.context_base + global_url_map.home_url;
            } else {
                toastr.error(result.msg);
            }
        },
        error:function () {
            toastr.error('删除失败');
        }
    });
}
