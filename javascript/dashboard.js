// ── Days remaining calc ──
(function() {
    const payroll = new Date('2025-12-25');
    const today = new Date();
    today.setHours(0,0,0,0);
    const diff = Math.ceil((payroll - today) / (1000*60*60*24));
    const el = document.getElementById('daysRemaining');
    if (el) el.textContent = diff > 0 ? diff : 0;
})();

// ── Chart data ──
const baseData = {
    labels: ['1st Year', '2nd Year', '3rd Year', '4th Year'],
    BSHRM: [25, 80, 50, 100],
    BSIT:  [100, 60, 80, 80],
    BSOA:  [65, 80, 70, 20]
};

// ── Build Chart ──
const ctx = document.getElementById('deptChart').getContext('2d');
let deptChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: baseData.labels,
        datasets: [
            {
                label: 'BSHRM',
                data: baseData.BSHRM,
                backgroundColor: '#4ecdc4',
                borderRadius: 3,
                borderSkipped: false
            },
            {
                label: 'BSIT',
                data: baseData.BSIT,
                backgroundColor: '#1a8fa0',
                borderRadius: 3,
                borderSkipped: false
            },
            {
                label: 'BSOA',
                data: baseData.BSOA,
                backgroundColor: '#3d5a80',
                borderRadius: 3,
                borderSkipped: false
            }
        ]
    },
    options: {
        indexAxis: 'y',
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'top',
                align: 'center',
                labels: {
                    usePointStyle: true,
                    pointStyle: 'circle',
                    padding: 20,
                    font: { size: 12 }
                }
            },
            tooltip: {
                callbacks: {
                    label: ctx => ` ${ctx.dataset.label}: ${ctx.parsed.x}`
                }
            }
        },
        scales: {
            x: {
                min: 0,
                max: 100,
                ticks: { stepSize: 20, font: { size: 12 } },
                grid: { color: 'rgba(0,0,0,0.06)' }
            },
            y: {
                ticks: { font: { size: 13 }, color: '#333' },
                grid: { display: false }
            }
        },
        layout: { padding: { right: 8 } }
    }
});