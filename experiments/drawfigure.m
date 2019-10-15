function drawfigure(inputfolder, filename, outputfolder)

fid = fopen(strcat(inputfolder, '/', filename));
tline = fgetl(fid); %get the first line
ttpfile = tline;
disp(ttpfile);
tline = fgetl(fid);

count = 0;
w = [];
b = [];

bestweight = 0;
bestbenefit = - Inf;
besttour = ttpfile;
tour = ttpfile;

hold on

while ischar(tline)
    strline = strtrim(string(tline));
    if endsWith(strline, 'tour') || endsWith(strline, 'reversed')
        disp(count);
        count = 0;
        disp(strline);
        tour = strline;
        
        stairs(w, b);
        
%         disp(w);
%         disp(b);
        w = [];
        b = [];
        tline = fgetl(fid);
        continue
    end
    
    if startsWith(strline, 'weight') || strlength(strline) == 0
        tline = fgetl(fid);
        continue
    end
    
    d = str2double(strsplit(strline));
    w = [w, d(1)];
    b = [b, d(2)];
    count = count+1;
    tline = fgetl(fid);
    
    if b(end) > bestbenefit
       bestbenefit = b(end);
       besttour = tour;
       bestweight = w(end);
    end
    
end

disp(count);

fclose(fid);
stairs(w, b);

bestlength = tsplength(besttour);
tl = strcat(...%algoname(filename), ...
    'Max reward: ', num2str(bestbenefit), ...%     ', weight:', int2str(bestweight), ...
    ' Corresponding tour length:', int2str(bestlength) ...
    ); 

title(tl,'FontWeight','Normal');
xlabel('Weight');
ylabel('Total Reward');
xlim([0 4000]);

% set(gca,'position',[0.065 0.075 0.93 0.895],'units','normalized')
set(gcf,'units','points','position',[52,247,334,208]);

print(strcat(outputfolder, '/', filename, '.eps'),'-depsc');
% savefig(strcat(outputfolder, '/', filename, '.fig'));

close;

end

