filename = 'eil76_n75_uncorr_01_hypervolume.txt';

T = readtable(filename);

x = T{:,1};
y = T{:,2:3};

stairs(x,y, 'LineWidth',2);

ylabel('Hyper-volume');
xlabel('No. of Iteration');
legend('LHV', 'LSC', 'Location','southeast');