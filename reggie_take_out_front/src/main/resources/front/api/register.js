function registerApi(data) {
    return $axios({
        'url': '/user/register',
        'method': 'post',
        data
    })
}

function getCodApi(data) {
    return $axios({
            'url' : '/user/code',
            'method': 'get',
            params:{...data}
        })
}