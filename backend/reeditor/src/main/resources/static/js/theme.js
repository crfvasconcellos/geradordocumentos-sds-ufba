(function(){
    const KEY = 'site-theme-dark';
    const btn = document.getElementById('themeBtn');
    if(!btn) return;

    function apply(isDark){
        // aplicar tanto no <html> quanto no <body> para que variáveis CSS sejam herdadas corretamente
        document.documentElement.classList.toggle('dark', !!isDark);
        document.body.classList.toggle('dark', !!isDark);
        btn.setAttribute('aria-pressed', !!isDark);
    }

    // carregar preferência
    const saved = localStorage.getItem(KEY);
    if(saved !== null){
        apply(saved === '1');
    } else {
        // detectar preferencia do sistema
        const prefersDark = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
        apply(prefersDark);
    }

    btn.addEventListener('click', function(e){
        const isDark = !document.body.classList.contains('dark');
        apply(isDark);
        localStorage.setItem(KEY, isDark ? '1' : '0');
    });
})();
